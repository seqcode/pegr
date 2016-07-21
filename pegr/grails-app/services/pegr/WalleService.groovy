package pegr
import grails.converters.XML
import grails.transaction.Transactional
import groovy.io.FileType
import ssh.RemoteSSH
import ssh.RemoteSCP
import ssh.RemoteSCPDir
import groovy.json.*

class WalleService {

    def grailsApplication
    def sshConfig

    final String RUNS_IN_QUEUE = "RunsInQueue"
    final String PRIOR_RUN_FOLDER = "PriorRunFolder"
    final String LOCAL_FOLDER = "files/runInfos"
    final String RUN_INFO_FILE_NAME = "cegr_run_info.txt"
    final String CONFIG_FOLDER_NAME = "cegr_config"
    final int MAX_QUEUE_LENGTH = 24
    
    Map getWalle() {
        def walle = [
            host : grailsApplication.config.walle.host,
            port : grailsApplication.config.walle.port.toInteger(),
            username : grailsApplication.config.walle.username,
            password : grailsApplication.config.walle.password,
            root : grailsApplication.config.walle.root
        ]
        return walle
    }
    
    void addToQueue(Long runId) {
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        if (runsInQueue) {
            if (runsInQueue.value && runsInQueue.value != "") {
                runsInQueue.value += ",${runId}"
            } else {
                runsInQueue.value = runId
            }
            runsInQueue.save()        
        } else {
            new Chores(name: RUNS_IN_QUEUE, value: "${runId}").save()
        }    
    }
    
    def getPreviousRun() {
        def previousRun = null
        String previousRunFolder = Chores.findByName(PRIOR_RUN_FOLDER)?.value
        if (previousRunFolder) {
            previousRun = SequenceRun.findByDirectoryName(previousRunFolder)
        }
        return previousRun
    }
    
    void createJob() {
        
        def walle = getWalle()
        
        def runIds = getQueuedRunIds()
        // return if no runs in the queue
        if (runIds.size() == 0) {
            return
        }        
        if (runIds.size() > MAX_QUEUE_LENGTH) {
            // log exceeded queue length
            String message = "More than ${MAX_QUEUE_LENGTH} waiting in the queue!" 
            log.error message
        }
        // get the run object
        Long runId = Long.parseLong(runIds[0])
        def run = SequenceRun.get(runId)
        // return if the prior run has not been processed by the remote server
        def remoteFiles = getRemoteFiles()
        if (findPriorInfoOnRemote(remoteFiles)) {
            log.warn "The last run has not been processed yet!"
            return
        }
        // get new folder name on the remote server
        def newRunFolders = getNewRunFolders(remoteFiles)
        // return if no new folder has been created on the remote server
        if (newRunFolders.size() == 0) {
            return
        }        
        def newFolder = newRunFolders.first()
        def newRunRemotePath = new File(walle.root, newFolder).getPath()
        // update run object
        run.directoryName = newFolder
        def d = newFolder.split("_").last()
        run.fcId = d[1..-1]
        run.status = RunStatus.RUN
        run.save()
        // generate run info and parameter files
        def fileAndFolder = generateRunFiles(run, newRunRemotePath)
        // move files to the remote server
        moveFilesToRemote(fileAndFolder.runInfoLocalFile, fileAndFolder.configLocalFolder, newRunRemotePath)
        // update queue
        removeRunFromQueue()  
        updatePriorRunFolder(newFolder)
        log.info "WallE service has sent the info of run ${run.id} to Wall E."
    }
    
    def getQueuedRunIds() {
        def runIds = []
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        if (runsInQueue?.value && runsInQueue.value != "") {
            runIds = runsInQueue.value.split(",")
        }
        return runIds
    }
    
    def findPriorInfoOnRemote(String[] remoteFiles) {
        def runInfoPath = new File(walle.root, RUN_INFO_FILE_NAME).getPath()
        return remoteFiles.find{ it == runInfoPath}
    }
    
    def getRemoteFiles() {
        def walle = getWalle()
        def command = 'ls ' + walle.root + ' | sort'
        def rsh = new RemoteSSH(walle.host, walle.username, walle.password, '', command, '', walle.port)
        def s = rsh.Result(sshConfig).toString().split('<br>')
        return s
    }
    
    def getNewRunFolders(String[] remoteFiles) {
        String priorRunFolder = Chores.findByName(PRIOR_RUN_FOLDER)?.value
        // get all the folder names 
        
        def newPaths = []

        remoteFiles.each{
            if (!it.contains("exit") && !it.contains('ls ') && !it.contains(RUN_INFO_FILE_NAME)) {
                if (priorRunFolder == null || it > priorRunFolder) {
                    newPaths.push(it)
                }
            }
        }
        return newPaths
    }
    
    def generateRunFiles(SequenceRun run, String newRunRemotePath) {
        // make the directory
        File localFolder = new File(LOCAL_FOLDER); 
        if (!localFolder.exists()) { 
            localFolder.mkdirs(); 
        } 
        // create the "cegr_run_info.txt" file
        File runInfoLocalFile = new File(localFolder, RUN_INFO_FILE_NAME)
        // clean up folder
        runInfoLocalFile.delete()
        runInfoLocalFile.createNewFile();
        // create the "cegr_config" folder
        File configLocalFolder = new File(localFolder, CONFIG_FOLDER_NAME)
        if (!configLocalFolder.exists()) { 
            configLocalFolder.mkdirs(); 
        } else {
            // clean up
            configLocalFolder.eachFile() {
                it.delete()
            }
        }
        // get parameters
        runInfoLocalFile.withWriter{
            // write the run folder path
            it.println newRunRemotePath
            run.experiments.each { experiment -> 
                def xmlNames = []
                def genomesStr = experiment.sample.requestedGenomes
                if (genomesStr) {
                    def genomes = genomesStr.split(",")
                    genomes.eachWithIndex { genome, idx ->
                        def xmlName = generateXmlFile(genome, run.id, experiment.sample.id, idx, configLocalFolder)
                        xmlNames.push(xmlName)
                    }
                }
                def indicesString = experiment.sample?.sequenceIndicesString
                def xmlNamesString= xmlNames.join(",")
                def data = "${run.id} ; ${experiment.sample?.id} ; ${indicesString} ; ${xmlNamesString}"           
                it.println data
            }
        }
        return [runInfoLocalFile: runInfoLocalFile, configLocalFolder: configLocalFolder]
    }
    
    def moveFilesToRemote(File runInfoLocalFile, File configLocalFolder, String newRunRemotePath) {  
        def walle = getWalle()
        // scp run info txt
        RemoteSCP rscp=new RemoteSCP(walle.host, walle.username, walle.password, runInfoLocalFile.getPath(), walle.root, walle.port)
        rscp.Result(sshConfig)
        // scp parameter config folder
        def remoteConfigPath = new File(newRunRemotePath, CONFIG_FOLDER_NAME).getPath()
        def command = 'mkdir ' + remoteConfigPath
        def rsh = new RemoteSSH(walle.host, walle.username, walle.password, '', command, '', walle.port)
        rsh.Result(sshConfig)
        RemoteSCPDir rscpdir=new RemoteSCPDir(walle.host, walle.username, walle.password, configLocalFolder.getPath(), remoteConfigPath, walle.port)
        rscpdir.Result(sshConfig)
        // cleanup the local files
        runInfoLocalFile.delete()
        configLocalFolder.eachFile() {
            it.delete()
        }
    }
    
    String generateXmlFile(String genome, Long runId, Long sampleId, int idx, File folder) {
        def filename = "${sampleId}_${idx}.xml"
        def file = new File(folder, filename)
        def alignmentParams = new WorkFlow(dbkey: genome)
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