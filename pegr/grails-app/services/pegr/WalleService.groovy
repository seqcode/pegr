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
    final String host = grailsApplication.config.walle.host
    final String port = grailsApplication.config.walle.port
    final String username = grailsApplication.config.walle.username
    final String password = grailsApplication.config.walle.password
    final String RUNS_IN_QUEUE = "RunsInQueue"
    final int MAX_QUEUE_LENGTH = 10
    final String WALLE_ROOT = "/home/nextseq/NSQData_PughLab"
    
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
        }
        // return if the prior run has not been processed by the remote server
        if (findPriorInfoOnRemote()) {
            return
        }
        // get new folder name 
        def newRunFolders = getNewRunFolders(priorRunFolder)
        // return if no new folder has been created on the remote server
        if (newRunFolders.size() == 0) {
            return
        }        
        if (newRunFolders.size() > 1) {
            // notify the run user of multiple new folders on the remote server
            
            return
        }
        def newFolder = newRunFolders.first()
        // update run object
        run.directoryName = newFolder
        def d = newFolder.split("_")
        run.fcId = d[1..-1]
        run.save()
        // generate run info and parameter files
        generate
        // move run info and parameter xml to the remote server
        moveRunFiles(run)
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
        def command = '[ -f cegr_run_info.txt ] && echo yes'
        def rsh = new RemoteSSH(host, username, passwrod, '', command, '', port)
        return (rsh.Result(sshConfig) == "yes") 
    }
    


    
    def getNewRunPaths() {
        String priorRunPath = Chores.findByName("PriorRunPath")?.value
        // get all the folder names 
        def command = 'ls | sort'
        def rsh = new RemoteSSH(host, username, passwrod, '', command, '', port)
        def s = rsh.Result(sshConfig)
        def newPaths = []
        s.eachLine{
            if (it > priorRunPath) {
                newPaths.push(it)
            }
        }
        return newPaths
    }
    
    Long generateRunFiles(SequenceRun run) {
        // make the directory
        def folderName = "files/runInfos/${run.id}"
        File folder = new File(folderName); 
        if (!folder.exists()) { 
            folder.mkdirs(); 
        } 
        // create the "cegr_run_info.txt" file (without the first line of run folder path)
        File runInfoFile = new File(folder, "cegr_run_info.txt")
        runInfoFile.createNewFile();
        // get parameters
        runInfoFile.withWriter{
            run.experiments.each { experiment -> 
                def xmlNames = []
                experiment.alignments.eachWithIndex { alignment, idx ->
                    def xmlName = generateXmlFile(alignment, run.id, experiment.sample.id, idx, folder)
                    xmlNames.push(xmlName)
                }
                def indicesString = experiment.sample?.sequenceIndices.collect{it.sequence}.join(",")
                def xmlNamesString= xmlNames.join(",")
                def data = "${run.id} ; ${experiment.sample?.id} ; ${indicesString} ; ${xmlNamesString}"           
                it.println data
            }
        }      
        def thisModified = folder.lastModified()
        return thisModified
    }
    
    String generateXmlFile(SequenceAlignment alignment, Long runId, Long sampleId, int idx, File folder) {
        def filename = "${sampleId}_${idx}.xml"
        def file = new File(folder, filename)
        def alignmentParams = new Payload( 
            parameters : new Parameters (
                step : [new Step( param: [
                                new Param(name: "reference_genome", value: alignment.genome.name)
                            ])
                       ]
            ))
        def converter = alignmentParams as XML
        converter.render(new java.io.FileWriter(file))
        return filename
    }

    // update run info with the path
    def updateRunInfoWithNewPath(newPath) {
        
    }
    
    // update the priorRunPath
    def updatePriorRunPath(newPath) {
        
    }
    
    // move run info and parameter xml to the remote server
    def moveRunFiles(run) {
        
    }

}

class Payload {
    Parameters parameters
}

class Parameters {
    Step[] step
}

class Step {
    Param[] param
}

class Param {
    String name
    String value
}