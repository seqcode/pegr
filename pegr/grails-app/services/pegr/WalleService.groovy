package pegr
import grails.converters.XML
import grails.transaction.Transactional
import groovy.io.FileType
import groovy.json.*

class WalleException extends RuntimeException {
    String message
}
    
/**
 * Class of service that connects and sends run information to 
 * Sequencer Wall E
 */
class WalleService {

    def grailsApplication
    def utilityService

    // Names used to query stored information in Table Chores.
    final String RUNS_IN_QUEUE = "RunsInQueue"
    final String PRIOR_RUN_FOLDER = "PriorRunFolder"
    // File and folder path
    final String LOCAL_FOLDER = "runInfos"
    final String RUN_INFO_FILE_NAME = "cegr_run_info.txt"
    final String CONFIG_FOLDER_NAME = "cegr_config"
    final String RUN_COMPLETE_FILE = "RunCompletionStatus.xml"
    // max queue length, error will be logged if it is exceeded.
    final int MAX_QUEUE_LENGTH = 24
    
    /**
     * Get Wall E connection configs from grails configs
     * @return Wall E connection configs
     */
    Map getWalle() {
        def walle = [
            // username to login Wall E
            username : grailsApplication.config.walle.username,
            // rsa private file
            keyfile : grailsApplication.config.walle.keyfile,
            // Wall E's hostname
            host : grailsApplication.config.walle.host,
            // Wall E's open port
            port : grailsApplication.config.walle.port,
            // folder that contains all the sequence runs
            root : grailsApplication.config.walle.root
        ]
        return walle
    }
    
    /**
     * Append a sequence run to the end of a first-in-first-out queue,
     * which consists of a list of sequence run IDs, delimited by ','. 
     * The information of the sequence runs will be sequentially sent 
     * to remote server.
     * @param runId ID of the sequence run to be appended to the queue
     */
    void addToQueue(Long runId) {
        // query the current queue
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        // if the queue has been defined
        if (runsInQueue) {
            // if the queue is not empty, append run ID to the end.
            if (runsInQueue.value && runsInQueue.value != "") {
                runsInQueue.value += ",${runNum}"
            } else {
                // else, set run ID to be the only one in queue
                runsInQueue.value = runNum
            }
            runsInQueue.save()        
        } else {
            // else, create the queue and put run ID into queue
            new Chores(name: RUNS_IN_QUEUE, value: "${runNum}").save()
        }    
    }
    
    /**
     * Get the previous run that has its information sent to remote 
     * server.
     * @return the previous sequence run object. If no previous run is 
     * found, return null.
     */
    def getPreviousRun() {
        // set default value of previous run to null. 
        def previousRun = null
        // query Table Chores to get the previous run's folder name on remote server. 
        String previousRunFolder = Chores.findByName(PRIOR_RUN_FOLDER)?.value
        if (previousRunFolder) {
            // query the sequence run that is linked to that folder.
            previousRun = SequenceRun.findByDirectoryName(previousRunFolder)
        }
        return previousRun
    }
    
    /** 
     * Create a job to query queued sequence runs and the information 
     * on remote server. If there is sequence run(s) waiting in the 
     * queue and the previous run's information has been moved, send
     * the information of the first run in queue to remote server. 
     */
    void createJob() {
        // get Wall E's connection information 
        def walle = getWalle()
        
        // get sequence run IDs in the queue
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
        
		
		
		// get the first sequence run in the queue
        //Long runId = Long.parseLong(runIds[0])
        def run = SequenceRun.findByRunNum(runIds[0].toInteger())//.get(runId)
        
        // return if the prior run has not been processed by the remote server
        def remoteFiles = getRemoteFiles()
        if (findPriorInfoOnRemote(remoteFiles)) {
            log.warn "The last run has not been processed yet!"
            return
        }
        
        // get new folder names on the remote server
        def newRunFolders = getNewRunFolders(remoteFiles)
        
        // return if no new folder has been created on the remote server
        if (newRunFolders.size() == 0) {
            return
        } 
        
        // get the first new folder's path on remote server
        def newFolder = newRunFolders.first()
        def newRunRemotePath = new File(walle.root, newFolder).getPath()
        
        // return if the sequence run has not completed
        if (!runCompleted(newRunRemotePath)) {
            return
        }
        
        // update sequence run's directory, FCID and status
        run.directoryName = newFolder
        def d = newFolder.split("_").last()
        run.fcId = d[1..-1]
        run.status = RunStatus.ANALYZING
        run.save()
        
        // generate run info and parameter files in local folder.
        def fileAndFolder = generateRunFiles(run, newRunRemotePath)
        
        // move files to the remote server
        moveFilesToRemote(fileAndFolder.runInfoLocalFile, fileAndFolder.configLocalFolder, newRunRemotePath)
        
        // update queue and update prior run folder in Chores
        removeRunFromQueue()  
        updatePriorRunFolder(newFolder)
        
        log.warn "WallE service has sent the info of run ${run.id} to Wall E."
    }
    
    /**
     * Get sequence run IDs in the queue
     * @return sequence run IDs in the queue
     */
    def getQueuedRunIds() {
        def runIds = []
        def runsInQueue = Chores.findByName(RUNS_IN_QUEUE)
        if (runsInQueue?.value && runsInQueue.value != "") {
            runIds = runsInQueue.value.split(",")
        }
        return runIds
    }
    
    /**
     * Given a list of remote file/folder names, find the run info file.
     * @param remoteFiles a list of file/folder names on the remote server.
     * @return the run info file. If not found, return null.
     */
    def findPriorInfoOnRemote(List remoteFiles) {
        return remoteFiles.find { it == RUN_INFO_FILE_NAME }
    }
    
    /**
     * Get the file and folder names under defined root folder on the 
     * remote server.
     * @return a list of file and folder names
     */
    def getRemoteFiles() {
        // gather information for ssh to Wall E
        def walle = getWalle()
        
        // set timeout to 2 min
        def timeout = 1000 * 60 * 2; 
        
        // command to list and sort all files and folder on Walle
        def command = "ssh -i ${walle.keyfile} ${walle.username}@${walle.host} ls ${walle.root} | sort"
        
        def remoteFiles
        try {
            // execute the command
            def output = utilityService.executeCommand(command, timeout)
            // convert the output to a list of file/folder names
            remoteFiles = output.readLines()*.trim()
        } catch (UtilityException e) {
            throw new WalleException(message: "Error connecting to Walle!")
        }
        return remoteFiles
    }
    
    def runCompleted(String newRunRemotePath) {
        def walle = getWalle()
        
        // set timeout to 2 min
        def timeout = 1000 * 60 * 2; 
        def newRunCompleteFile = new File(newRunRemotePath, RUN_COMPLETE_FILE).getPath()
        // command to list and sort all files and folder on Walle
        def command = "ssh -i ${walle.keyfile} ${walle.username}@${walle.host} ls ${newRunCompleteFile}"
        def result = false
        try {
            // execute the command
            utilityService.executeCommand(command, timeout)
            result = true
        } catch (UtilityException e) {
            // run not completed yet
        }
        return result
    }
    
    /**
     * Given a list of remote file/folder names, get the new folder 
     * names after the prior run.
     * @remoteFiles a list of file/folder names
     * @return a list of new folder names
     */
    def getNewRunFolders(List remoteFiles) {
        // get the prior run's folder name
        String priorRunFolder = Chores.findByName(PRIOR_RUN_FOLDER)?.value
        
        def newPaths = []
        remoteFiles.each {
            // skip run info file, and compare to the prior run's folder
            if (it != RUN_INFO_FILE_NAME 
                && ( priorRunFolder == null || it > priorRunFolder)) {
                // put the new folder path to the list
                newPaths.push(it)
            }
        }
        return newPaths
    }
    
    def generateRunFiles(SequenceRun run, String newRunRemotePath) {
        def filesroot = utilityService.getFilesRoot()
        File localFolder = new File(filesroot, LOCAL_FOLDER)
        generateRunFilesInFolder(run, newRunRemotePath, localFolder)
    }
    
    
    /**
     * Generate run info and config files in local folder.
     * @param run sequence run whose information will be sent to remote.
     * @param newRunRemotePath the new folder's path on remote server
     * @param localFolder the local folder to temporarily store the run infos
     * @return a map of (1) runInfoLocalFile: local run info file path,
     * (2) configLocalFolder: local config folder path.
     */
    def generateRunFilesInFolder(SequenceRun run, String newRunRemotePath, File localFolder) {
        // make the local folder to temporarily store run infos and 
        // configs if the folder does not exit.        
        if (!localFolder.exists()) { 
            localFolder.mkdirs() 
        } 
        
        // delete old run info if it exists and create a new run info file.
        File runInfoLocalFile = new File(localFolder, RUN_INFO_FILE_NAME)
        runInfoLocalFile.delete()
        runInfoLocalFile.createNewFile();
        
        // create the config folder if it doesn't exist; 
        // or clean up the folder if it already exists.
        File configLocalFolder = new File(localFolder, CONFIG_FOLDER_NAME)
        if (!configLocalFolder.exists()) { 
            configLocalFolder.mkdirs(); 
        } else {
            // clean up
            configLocalFolder.eachFile() {
                it.delete()
            }
        }
        
        // write to run info file
        runInfoLocalFile.withWriter{
            // write the remote run folder path
            it.println newRunRemotePath
            
            // for each sample
            run.experiments.each { experiment -> 
                def xmlNames = []
                // get requested genomes for each sample
                def genomesStr = experiment.sample.requestedGenomes
                if (genomesStr) {
                    def genomes = genomesStr.split(",")
                    // write the config xml file for each genome.
                    genomes.eachWithIndex { genome, idx ->
                        def xmlName = generateXmlFile(genome, run.runNum, experiment.sample.id, idx, configLocalFolder)
                        xmlNames.push(xmlName) //axa677: Careful!!!!!!
                    }
                }
                // write the sample's runID, sampleID, indices and config xml file names.
                def indicesString = experiment.sample?.sequenceIndicesString
                def xmlNamesString= xmlNames.join(",")
                //axa677: Careful!!!!!!
				def data = "${run.runNum} ; ${experiment.sample?.id} ; ${indicesString} ; ${xmlNamesString}"           
                it.println data
            }
        }
        return [runInfoLocalFile: runInfoLocalFile, configLocalFolder: configLocalFolder]
    }
    
    /**
     * Move local run info file and config folder to remote server.
     * @param runInfoLocalFile locol run info file
     * @param configLocalFolder local config folder
     * @param newRunRemotePath the new run's folder path on remote server
     */
    def moveFilesToRemote(File runInfoLocalFile, File configLocalFolder, String newRunRemotePath) {
        // get Wall E connection information
        def walle = getWalle()
        
        // scp run info file to remote server
        def runInfoLocalPath = runInfoLocalFile.getPath()
        def command = "scp -i ${walle.keyfile} ${runInfoLocalPath} ${walle.username}@${walle.host}:${walle.root}"
        def timeout = 1000 * 60 * 2 // 2 min
        try {
            utilityService.executeCommand(command, timeout)
        } catch (UtilityException e) {
            throw new WalleException(message: "Error sending run info!")
        }
        
        // scp config folder to remote server
        def configLocalPath = configLocalFolder.getPath()
        def configRemotePath = new File(newRunRemotePath, CONFIG_FOLDER_NAME).getPath()
        command = "scp -i ${walle.keyfile} -r ${configLocalPath} ${walle.username}@${walle.host}:${configRemotePath}"
        timeout = 1000 * 60 * 5 // 5 min
        try {
            utilityService.executeCommand(command, timeout)
        } catch (UtilityException e) {
            throw new WalleException(message: "Error sending config files!")
        }
        
        // cleanup the local files
        runInfoLocalFile.delete()
        configLocalFolder.eachFile() {
            it.delete()
        }
    }
    
    /**
     * Generate config xml files
     * @param genome the name of the genome build
     * @param runId the sequence run's ID
     * @param sampleId sample Id
     * @param idx the index of the genome build for the sample
     * @param folder the config folder
     * @return the filename of the xml config file
     */
    String generateXmlFile(String genome, Long runId, Long sampleId, int idx, File folder) {
        // create the config xml file
        def filename = "${sampleId}_${idx}.xml"
        def file = new File(folder, filename)
        
        // create the config object
        def alignmentParams = new WorkFlow(dbkey: genome)
        
        // convert the config object to xml
        def converter = alignmentParams as XML
        
        // write to the config xml file
        converter.render(new java.io.FileWriter(file))
        return filename
    }
    
    /**
     * Remove the processed sequence run from the queue.
     */
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

    /**
     * Update the Prior Run Folder in Chores.
     * @param newFolder the name of the newly processed folder.
     */
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
    
    def getQueue() {
        return [previousRunFolder: Chores.findByName(PRIOR_RUN_FOLDER)?.value,
                queuedRuns: Chores.findByName(RUNS_IN_QUEUE)?.value]
    }
    
    def updateQueue(String previousRunFolder, String queuedRuns) {
        // update previous run's folder
        def previousRunFolderConfig = Chores.findByName(PRIOR_RUN_FOLDER)
        if (previousRunFolderConfig && previousRunFolderConfig.value != previousRunFolder) {
            previousRunFolderConfig.value = previousRunFolder
            previousRunFolderConfig.save(failOnError: true)
        }
        // check teh format of queuedRuns        
        if (queuedRuns == "") {
            queuedRuns = null
        } else {
            def queueIds = queuedRuns.split(",")
            def ids = []
            queueIds.each { idRaw ->
                def id = idRaw.trim()
                if (!id.isInteger()) {
                    throw new WalleException(message: "Format error in queued runs!${id}!")
                } else {
                    ids.push(id)
                }
            }
            queuedRuns = ids.join(",")
        }
        
        // update the queuedRuns
        def queuedRunsConfig = Chores.findByName(RUNS_IN_QUEUE)
        if (queuedRunsConfig && queuedRunsConfig.value != queuedRuns) {
            queuedRunsConfig.value = queuedRuns
            queuedRunsConfig.save(failOnError: true)
        }
    }
}

/**
 * Class for the config xml file. 
 */
class WorkFlow {
    String dbkey
}