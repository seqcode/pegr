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
    
    final String host = grailsApplication.config.walle.host
    final String port = grailsApplication.config.walle.port
    final String username = grailsApplication.config.walle.username
    final String password = grailsApplication.config.walle.password
    final String RUNS_IN_QUEUE = "RunsInQueue"
    final String PRIOR_RUN_FOLDER = "PriorRunFolder"
    final String LOCAL_FOLDER = "files/runInfos"
    final String RUN_INFO_FILE_NAME = "cegr_run_info.txt"
    final String CONFIG_FOLDER_NAME = "cegr_config"
    final int MAX_QUEUE_LENGTH = 24
    final String WALLE_ROOT = "/home/nextseq/NSQData_PughLab/"
    
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
                to "shaody8411@gmail.com"
                subject "[PEGR]${message}"
                body message
            }
        }
        // return if the prior run has not been processed by the remote server
        if (findPriorInfoOnRemote()) {
            return
        }
        // get new folder name on the remote server
        def newRunFolders = getNewRunFolders()
        // return if no new folder has been created on the remote server
        if (newRunFolders.size() == 0) {
            return
        }        
        if (newRunFolders.size() > 1) {
            // notify the run user of multiple new folders on the remote server
            String message = "More than 1 new folders found on Wall E!"
            log.error message
            mailService.sendMail {
                to "shaody8411@gmail.com"
                subject "[PEGR]${message}"
                body message
            }
            return
        }
        def newFolder = newRunFolders.first()
        // update run object
        Long runId = Long.parseLong(runIds[0])
        def run = SequenceRun.get(runId)
        run.directoryName = newFolder
        def d = newFolder.split("_")
        run.fcId = d[1..-1]
        run.save()
        // generate run info and parameter files and send it to remote server
        generateRunFiles(run, newFolder)
        // update queue
        updateRunsInQueue()                
    }

    
    int getQueuedRunIds() {
        def runIds = []
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        if (runsInQueue && runsInQueue != "") {
            runsIds = runsInQueue.value.split(",")
        }
        return runIds
    }
    
    def findPriorInfoOnRemote() {
        def runInfoPath = new File(WALLE_ROOT, RUN_INFO_FILE_NAME).getPath()
        def command = '[ -f ${runInfoPath} ] && echo yes'
        def rsh = new RemoteSSH(host, username, passwrod, '', command, '', port)
        return (rsh.Result(sshConfig) == "yes") 
    }
    
    def getNewRunFolders() {
        String priorRunFolder = Chores.findByName(PRIOR_RUN_FOLDER)?.value
        // get all the folder names 
        def command = 'ls ${WALLE_ROOT} | sort'
        def rsh = new RemoteSSH(host, username, passwrod, '', command, '', port)
        def s = rsh.Result(sshConfig)
        def newPaths = []
        s.eachLine{
            if (it > priorRunFolder) {
                newPaths.push(it)
            }
        }
        return newPaths
    }
    
    Long generateRunFiles(SequenceRun run, String newFolder) {
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
        // write the run folder path
        def newRunRemotePath = new File(WALLE_ROOT, newFoler).getPath()
        // get parameters
        runInfoLocalFile.withWriter{
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
        RemoteSCP rscp=new RemoteSCP(host, username, password, runInfoLocalFile, WALLE_ROOT, port)
        RemoteSCPDir rscpdir=new RemoteSCPDir(host, username, password, configLocalFolder, newRunRemotePath, port)
        // cleanup the local files
        runInfoLocalFile.delete()
        configLocalFolder.eachFile() {
            it.delete()
        }
    }
    
    String generateXmlFile(SequenceAlignment alignment, Long runId, Long sampleId, int idx, File folder) {
        def filename = "${sampleId}_${idx}.xml"
        def file = new File(folder, filename)
        def alignmentParams = "TODO"
        def converter = alignmentParams as XML
        converter.render(new java.io.FileWriter(file))
        return filename
    }
    
    def updateRunsInQueue() {
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
    
}

