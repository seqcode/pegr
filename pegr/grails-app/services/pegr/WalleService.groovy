package pegr
import grails.converters.XML
import grails.transaction.Transactional
import groovy.io.FileType
import ssh.RemoteSSH
import ssh.RemoteSCP
import ssh.RemoteSCPDir
import groovy.json.*

class WalleService {
    
    def mailService
    def grailsApplication
    def sshConfig

    final String RUNS_IN_QUEUE = "RunsInQueue"
    final String PRIOR_RUN_FOLDER = "PriorRunFolder"
    final String LOCAL_FOLDER = "files/runInfos"
    final String RUN_INFO_FILE_NAME = "cegr_run_info.txt"
    final String CONFIG_FOLDER_NAME = "cegr_config"
    final int MAX_QUEUE_LENGTH = 24
    
    void addToQueue(Long runId) {
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        if (runsInQueue) {
            runsInQueue.value += ",${runId}"
            runsInQueue.save()
        } else {
            new Chores(name: RUNS_IN_QUEUE, value: "${runId}").save()
        }    
    }
    
    void createJob() {
        if (grailsApplication.config == null) {
            log.error "grailsApplication null"
        }
        def walle = [
            host : grailsApplication.config.walle.host,
            port : grailsApplication.config.walle.port.toInteger(),
            username : grailsApplication.config.walle.username,
            password : grailsApplication.config.walle.password,
            root : grailsApplication.config.walle.root
        ]
        
        def runIds = getQueuedRunIds()
        // return if no runs in the queue
        if (runIds.size() == 0) {
            return
        }        
        if (runIds.size() > MAX_QUEUE_LENGTH) {
            // notify the run user of exceeded queue length
            String message = "More than ${MAX_QUEUE_LENGTH} waiting in the queue!" 
            log.error message
            mailService.sendMail {
                to "dus73@psu.edu"
                subject "[PEGR]${message}"
                body message
            }
        }
        // return if the prior run has not been processed by the remote server
        if (findPriorInfoOnRemote(walle)) {
            return
        }
        // get new folder name on the remote server
        def newRunFolders = getNewRunFolders(walle)
        // return if no new folder has been created on the remote server
        if (newRunFolders.size() == 0) {
            return
        }        
        if (newRunFolders.size() > 1) {
            // notify the run user of multiple new folders on the remote server
            String message = "More than 1 new folders found on Wall E!"
            log.error message
            mailService.sendMail {
                to "dus73@psu.edu"
                subject "[PEGR]${message}"
                body message
            }
            return
        }
        def newFolder = newRunFolders.first()
        def newRunRemotePath = new File(walle.root, newFolder).getPath()
        // update run object
        Long runId = Long.parseLong(runIds[0])
        def run = SequenceRun.get(runId)
        run.directoryName = newFolder
        def d = newFolder.split("_").last()
        run.fcId = d[1..-1]
        run.save()
        // generate run info and parameter files
        def fileAndFolder = generateRunFiles(run, newRunRemotePath)
        // move files to the remote server
        moveFilesToRemote(fileAndFolder.runInfoLocalFile, fileAndFolder.configLocalFolder, newRunRemotePath, walle)
        // update queue
        removeRunFromQueue()  
        updatePriorRunFolder(newFolder)
    }

    
    def getQueuedRunIds() {
        def runIds = []
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        if (runsInQueue && runsInQueue != "") {
            runIds = runsInQueue.value.split(",")
        }
        return runIds
    }
    
    def findPriorInfoOnRemote(Map walle) {
        def runInfoPath = new File(walle.root, RUN_INFO_FILE_NAME).getPath()
        def command = '[ -f ' + runInfoPath + ' ] && echo yes'
        def rsh = new RemoteSSH(walle.host, walle.username, walle.password, '', command, '', walle.port)
        if (!rsh) {
            log.error "rsh is null"
        }
        if (!sshConfig) {
            log.error "sshConfig is null"
        }
        return (rsh.Result(sshConfig) == "yes") 
    }
    
    def getNewRunFolders(Map walle) {
        String priorRunFolder = Chores.findByName(PRIOR_RUN_FOLDER)?.value
        // get all the folder names 
        def command = 'ls ' + walle.root + ' | sort'
        def rsh = new RemoteSSH(walle.host, walle.username, walle.password, '', command, '', walle.port)
        def s = rsh.Result(sshConfig)
        def newPaths = []

        s.eachLine{
            if (priorRunFolder == null || it > priorRunFolder) {
                newPaths.push(it)
            }
        }
        return newPaths
    }
    
    def generateAndSendRunFiles(SequenceRun run, String newRunRemotePath) {
        // make the directory
        File localFolder = new File(LOCAL_FOLDER); 
        if (!localFolder.exists()) { 
            localFolder.mkdirs(); 
        } 
        // create the "cegr_run_info.txt" file
        File runInfoLocalFile = new File(localFolder, RUN_INFO_FILE_NAME)
        runInfoLocalFile.createNewFile();
        // create the "cegr_config" folder
        File configLocalFolder = new File(localFolder, CONFIG_FOLDER_NAME)
        if (!configLocalFolder.exists()) { 
            configLocalFolder.mkdirs(); 
        }

        // get parameters
        runInfoLocalFile.withWriter{
            // write the run folder path
            it.println newRunRemotePath
            run.experiments.each { experiment -> 
                def xmlNames = []
                experiment.alignments.eachWithIndex { alignment, idx ->
                    def xmlName = generateXmlFile(alignment, run.id, experiment.sample.id, idx, configLocalFolder)
                    xmlNames.push(xmlName)
                }
                def indicesString = experiment.sample?.sequenceIndices.collect{it.sequence}.join(",")
                def xmlNamesString= xmlNames.join(",")
                def data = "${run.id} ; ${experiment.sample?.id} ; ${indicesString} ; ${xmlNamesString}"           
                it.println data
            }
        }
        return [runInfoLocalFile: runInfoLocalFile, configLocalFolder: configLocalFolder]
    }
    
    def moveFilesToRemote(File runInfoLocalFile, File configLocalFolder, String newRunRemotePath, Map walle, def sshConfig) {                   
        RemoteSCP rscp=new RemoteSCP(walle.host, walle.username, walle.password, runInfoLocalFile.getPath(), walle.root, walle.port)
        RemoteSCPDir rscpdir=new RemoteSCPDir(walle.host, walle.username, walle.password, configLocalFolder.getPath(), newRunRemotePath, walle.port)
        // cleanup the local files
        runInfoLocalFile.delete()
        configLocalFolder.eachFile() {
            it.delete()
        }
    }
    
    String generateXmlFile(SequenceAlignment alignment, Long runId, Long sampleId, int idx, File folder) {
        def filename = "${sampleId}_${idx}.xml"
        def file = new File(folder, filename)
        def alignmentParams = new WorkFlow(dbkey: alignment.genome.name)
        def converter = alignmentParams as XML
        converter.render(new java.io.FileWriter(file))
        return filename
    }
    
    def removeRunFromQueue() {
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        def oldStr = runsInQueue.value
        def p = oldStr.indexOf(',')
        if (p == -1) {
            runsInQueue.value = null
        } else {
            runsInQueue.value = oldStr[(p+1)..-1]
        }
        runsInQueue.save()
    }

    def updatePriorRunFolder(String newFolder) {
        def priorRunFolder = Chores.findByName(PRIOR_RUN_FOLDER)
        if (priorRunFolder) {
            priorRunFolder.value = newFolder
            priorRunFolder.save()
        } else {
            new Chores(name: PRIOR_RUN_FOLDER, value: newFolder).save()
        }
        return
    }
}

class WorkFlow {
    String dbkey
}