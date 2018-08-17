package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import groovy.time.TimeCategory
import groovy.json.*
import grails.converters.*
import org.apache.commons.io.IOUtils
import grails.util.Holders
    
class SequenceRunController {
    def springSecurityService    
    def sequenceRunService    
    def qfileUploadService    
    def walleService
    def reportService
    def utilityService
    def sampleService
    
    // list incomplete runs
    def index(Integer max, String str, String status){
        def c = SequenceRun.createCriteria()
        def listParams = [
                max: params.max ?: 50,
                sort: params.sort ?: "runNum", //if you would like to sort based on the runNum, just remeber it's basically for the old run number. //changed it back to runNum
                order: params.order ?: "desc",
                offset: params.offset ?: 0
            ]
        def runs
	def galaxy = Holders.config.defaultGalaxy
        if (status) {
            runs = c.list(listParams) {
                eq "status", (status as RunStatus)
            }
        } else if (str) {
            runs = c.list(listParams) {
                or {
                    if (str.isInteger()) {
                        eq "id", Long.parseLong(str)
                        eq "runNum", str.toInteger()
                    }
                    
                    ilike "fcId", "%${str}%"
                    
                    ilike "directoryName", "%${str}%"
                    user {
                        ilike "username", "%${str}%"
                    }
                    platform {
                        ilike "name", "%${str}%"
                    }
                }
            }
        } else {
            runs = SequenceRun.list(listParams) 
        }
        [runs: runs, str: str, defaultGalaxy: galaxy]
    }
    
    def show(Long id) {
        def run = SequenceRun.get(id)
        if (run) {
            def read = null
            if (run.experiments.size() > 0) {
                def jsonSlurper = new JsonSlurper()
                read = utilityService.parseJson(run.experiments[0].readPositions)   
                if (read) {
                    read.readType = run.experiments[0].readType
                }
            }
            def currUser = springSecurityService.currentUser
            def authorized = (run.user == currUser || currUser.isAdmin())
            def editable = (run?.status!=pegr.RunStatus.COMPLETED) && (authorized)
            [run: run, read: read, editable: editable]
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }
    
    def editRead(Long runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            def read = null
            if (run.experiments.getAt(0)?.readPositions) {
                def jsonSlurper = new JsonSlurper()
                read = jsonSlurper.parseText(run.experiments[0].readPositions)
                read.readType = run.experiments.getAt(0)?.readType
            }
            def indexType = read?.containsKey("index") ? "single" : "duo"
            [run: run, read: read, indexType: indexType]
        } else {
            flash.message = "Please add samples first!"
            redirect(action: "show", id: runId)
        }
    }
    
    def updateRead(Long runId, String indexType, String readType) {
        def readPositions
        def posMap
        if (indexType == "single") {
            posMap = [
                rd1: [params.rd1Start, params.rd1End], 
                index: [params.indexStart, params.indexEnd], 
                rd2: [params.rd2Start, params.rd2End]]
            
        } else {
            posMap = [
                rd1: [params.rd1Start, params.rd1End], 
                index1: [params.index1Start, params.index1End], 
                index2: [params.index2Start, params.index2End]]
        }
        if (readType == "PE") {
            posMap.rd2 = [params.rd2Start, params.rd2End]
        }
        readPositions = JsonOutput.toJson(posMap)
        try {
            sequenceRunService.updateRead(runId, readPositions, readType)
            flash.message = "Success updating the read type and read positions!"
        } catch(SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }
    
    def create() {
        def largestRunNum = SequenceRun.createCriteria().get {
            projections {
                max "runNum"
            }
        } as Long 
        [defaultRunNum: largestRunNum + 1]
    }
    
    def save() {
        def run = new SequenceRun(params)
        try {
            sequenceRunService.save(run)
            flash.message = "New sequence run created!"
            redirect(action: "show", params: [id: run.id])
        }catch(SequenceRunException e) {
            request.message = e.message
            def largestRunNum = SequenceRun.createCriteria().get {
                projections {
                    max "runNum"
                }
            } as Long 
            render(view: "create", model: [run:run, defaultRunNum: largestRunNum + 1]) 
        }
    }
    
    def editInfo(Long runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            [run: run]
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }
    // re - check again, later!
    def update(Long runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            try {
                run.properties = params 
                if (run.runStats) {
                    run.runStats.properties = params
                } else {
                    run.runStats = new RunStats(params)
                }
                sequenceRunService.save(run)                   
                redirect(action: "show", id:runId)
            } catch(SequenceRunException e) {
                request.message = e.message
                render(view: "editInfo", model: [run: run])
            }
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }
    // re - check again, later!
    def searchPool(Long runId, Long typeId, String barcode) {
        if (request.method == "POST") {
            def poolItem = Item.where {type.id == typeId && barcode == barcode}.find()
            if (poolItem) {
                render(view: "previewPool", model: [runId: runId, poolItem: poolItem])
            } else {
                request.message = "Pool not found!"
                [runId: runId]
            }
        } else {
            [runId: runId]   
        }
    }
    // re - check again, later!
    def addPool(Long poolItemId, Long runId) {
        try {
            sequenceRunService.addPool(poolItemId, runId)
            flash.message = "Success! All samples have been added to the sequence run."
        } catch (SequenceRunException e) {
            flash.message = e.message
        } catch (Exception e) {
            flash.message = "An unexpected error has occured!"
            log.error e
        }
        redirect(action: "show", params: [id: runId])
    }
    // re - check again, later!
    def removePool(Long runId) {
        try {
            sequenceRunService.removePool(runId)
            flash.message = "The master pool has been removed!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", params: [id: runId])
    }
    
    def addSamplesById(Long runId, String sampleIds) {
        try {
            def unknownSampleIds = sequenceRunService.addSamplesById(runId, sampleIds)
            if (unknownSampleIds.size() > 0) {
                flash.message = "Unknown Samples ${unknownSampleIds} are not added to the sequence Run!"
            } else {
                flash.message = "Success! All samples have been added to the sequence run."
            }           
        } catch(SequenceRunException e) {
            flash.message = e.message
        } catch (Exception e) {
            flash.message = "An unexpected error has occured!"
            log.error e
        }
        redirect(action: "show", params: [id: runId])
    }
    
    def removeExperiment(Long experimentId, Long runId) {
        try {
            sequenceRunService.removeExperiment(experimentId)
            flash.message = "Sequencing experiment deleted!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", params: [id: runId])
    }
    
    def updateSamples(Long runId) {
        def messages = ""
        def expIds = params.list('experimentId')
        expIds.each{
            def genomeIds = params.list("genomes${it}")
            try {
                sequenceRunService.updateSample(it, genomeIds)
            } catch (SequenceRunException e) {
                messages += "<p>e.message</p>"
            } 
        }
        if (messages == "") {
            messages = "All genomes added successfully!"
        }
        flash.message = messages
        redirect(action: "show", id: runId)
    }
    
    def previewRun(Long runId) {
        try {
            def previousRun = walleService.getPreviousRun()
            def remoteFiles = walleService.getRemoteFiles()
            def newFolders = walleService.getNewRunFolders(remoteFiles)
            def queuedRunIds = walleService.getQueuedRunIds()
            def queuedRuns = []
            queuedRunIds.eachWithIndex { id, n ->
                 def run = SequenceRun.get(Long.parseLong(id))
                queuedRuns.push([id: id, 
                                 runNum: run?.runNum, 
                                 directoryName: n < newFolders.size() ? newFolders[n] : null])

            }
            def currentRun = [id: runId,
                              runNum: SequenceRun.get(runId)?.runNum,
                              directoryName: queuedRunIds.size() < newFolders.size() ? newFolders[queuedRunIds.size()] : null]
            def startTime
            use(TimeCategory) {
                def now = new Date() 
                startTime = now.clearTime() + 10.hours + 1.week
            }
            [previousRun: previousRun,
             queuedRuns: queuedRuns,
             currentRun: currentRun,
             meetingTime: startTime]
        } catch (WalleException e) {
            flash.message = e.message
            redirect(action: "show", id: runId)
        } catch (Exception e) {
            log.error e
            flash.message = "Error querying the information!"
            redirect(action: "show", id: runId)
        }
    }
    
    def run(Long runId) {
        try {
            sequenceRunService.run(runId)
            redirect(action: "show", id: runId)
        } catch(SequenceRunException e) {
            flash.message = e.message
            redirect(action: "show", id: runId)
        }
    }
    
    def upload() { 
    
    }
    
    def convertXlsx() {
        def filesroot = utilityService.getFilesRoot()
        try {
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;  
            def mpf = mpr.getFile("file");
            String filename = mpf.getOriginalFilename();
            if(!mpf?.empty && filename[-5..-1] == ".xlsx") {  
                File folder = new File(filesroot, 'QueueFiles'); 
                if (!folder.exists()) { 
                    folder.mkdirs(); 
                } 
                File fileDest = new File(folder, filename)
                mpf.transferTo(fileDest)
                def user = springSecurityService.currentUser
                def basicCheck = true
                def messages = qfileUploadService.migrateXlsx(folder,
                                        fileDest.getPath(),
                                          params.sampleSheet,
                                          params.laneSheet,
                                          RunStatus.PREP, 
                                          params.int("startLine"), 
                                          params.int("endLine"),
                                          params.int("laneLine"),
                                          basicCheck
                                         )
                if (messages.size() == 0){
                    flash.messageList = ["The xlsx file uploaded!",]
                } else {
                    flash.messageList = messages                   
                }
            } else {
                flash.messageList = ["Only xlsx files are accepted!"]
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.messageList = ["Error uploading the file!"]
        }

        redirect(action: "index")
    }
    
    def convertCsv() {
        def filesroot = utilityService.getFilesRoot()
        try {
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;  
            def mpf = mpr.getFile("file");
            String filename = mpf.getOriginalFilename();
            if(!mpf?.empty && filename[-4..-1] == ".csv") {  
                File folder = new File(filesroot, 'QueueFiles'); 
                if (!folder.exists()) { 
                    folder.mkdirs(); 
                } 
                File fileDest = new File(folder, filename)
                mpf.transferTo(fileDest)
                def user = springSecurityService.currentUser
                def basicCheck = true
                def results = qfileUploadService.migrateSamples(fileDest.getPath(), 
                                          RunStatus.PREP, 
                                          params.int("startLine"), 
                                          params.int("endLine"),
                                          basicCheck
                                         )
                def messages = results.messages
                if (messages.size() == 0){
                    flash.messageList = ["CSV file uploaded!",]
                } else {
                    flash.messageList = messages                   
                }
            } else {
                flash.messageList = ["Only csv files are accepted!"]
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.messageList = ["Error uploading the file!"]
        }

        redirect(action: "index")
    }

    def fetchProjectsAjax(Long runId) {
        def projects = SequencingCohort.where{ run.id == runId }.collect {it.project?.name}.toList()
        render utilityService.stringToSelect2Data(projects) as JSON
    }
    
    def updateExperimentCohortAjax(Long runId, Long experimentId, String projectName) {
        sequenceRunService.updateExperimentCohort(runId, experimentId, projectName)
        render ""
    }
    
    def addProject(Long runId, Long projectId) {
        try {
            sequenceRunService.addProject(runId, projectId)
            flash.message = "Project added."
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }
    
    def removeProject(Long cohortId, Long runId) {
        try {
            sequenceRunService.removeProject(cohortId)
            flash.message = "Project removed."
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }
    
    def deleteProject(Long cohortId, Long runId) {
        try {
            sequenceRunService.deleteProject(cohortId)
            flash.message = "Project deleted."
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }
    
    def uploadCohortImage(Long cohortId, String type) {
        def cohort = SequencingCohort.get(cohortId)
        if (!cohort) {
            render(view: "/404")
            return
        }
        def runId = cohort.run.id
        try {
            def fieldName = "image"
            sequenceRunService.uploadCohortImage((MultipartHttpServletRequest)request, cohort, type, fieldName);
        } catch(SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", id: runId)
    }
    
    def removeCohortImageAjax(Long cohortId, String filepath) {
        sequenceRunService.removeCohortImage(cohortId, filepath)
        render ""
        return
    }
    
	//axa677-180808: Use runNum to name the files instead of run.id!!
    def downloadRunInfo(String remoteRoot, Long runId) {
        //String RUN_INFO_TEMP = "runInfo${runId}"
        def timeout = 60*1000
        
        // generate the run info files
        def run = SequenceRun.get(runId)
        String RUN_INFO_TEMP = "runInfo${run.runNum}" // I changed the id to runNum for naming only!
        // clean path
        remoteRoot = remoteRoot.trim()
        
        def remotePath 
        try {
            if (!remoteRoot || !run.directoryName) {
                throw new SequenceRunException()
            }
            remotePath = new File(remoteRoot, run.directoryName).getPath()
        } catch (Exception e) {
            flash.message = "Error! Please check the remote root and the directory name of Run #${runId}!"
            redirect(action: "show", id: runId)
        }
        def localRoot = utilityService.getFilesRoot()
        def localFolder = new File(localRoot, RUN_INFO_TEMP)
        if (!localFolder.exists()) { 
            localFolder.mkdirs() 
        } 
        walleService.generateRunFilesInFolder(run, remotePath, localFolder)
        
        // compress the files
        def compressedFilename = "runInfo${run.runNum}.tar.gz"
        def compressedFile = new File(localRoot, compressedFilename)
        def command = "tar -cf ${compressedFile.getPath()} ${RUN_INFO_TEMP}"
        def envVars = []
        def workDir = localRoot
        def out = command.execute(envVars, workDir)

        // remove the original run info files
        command = "rm -R ${localFolder.getPath()}"
        utilityService.executeCommand(command, timeout)
        
        InputStream contentStream
        try {
            response.setContentType("application/gzip") 
            response.setHeader("Content-disposition", "attachment;filename=\"${compressedFilename}\"")
            contentStream = compressedFile.newInputStream()
            response.outputStream << contentStream
            // remove the compressed file
            compressedFile.delete()
            webRequest.renderView = false
        } catch(Exception e) {
            render "Error!"
        } finally {
            response.getOutputStream().flush()
            response.getOutputStream().close()
            IOUtils.closeQuietly(contentStream)
        }

    }
    
    def editQueue() {
        def queue = walleService.getQueue()
        [previousRunFolder: queue.previousRunFolder, queuedRuns: queue.queuedRuns]
    }
    
    def updateQueue(String previousRunFolder, String queuedRuns) {
        try {
            walleService.updateQueue(previousRunFolder.trim(), queuedRuns.trim())
            flash.message = "Queue has been updated!"
        } catch (WalleException e) {
            flash.message = e.message
        }
        redirect(action: "editQueue")
    }
    
	// axa677-180306: No need for this function since we have written the next one to handle deletion using checkboxes
    def deleteSample(Long sampleId, Long runId) {
        try {
            def sample = Sample.get(sampleId)
            sampleService.delete(sample)
            flash.message = "Sample${sampleId} deleted!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "show", params: [id: runId])
    }
	
	
	// axa677-180306: Added this function to handle the deletion of selected (multiple) samples
	// This function is called in view sequenceRun/show.gsp 
	// it receives a list of samples ids and run id in a json dictionary and iterates over the ids
	// to send a request to sampleService.delete(sample) to delete each selected sample
	// Then, it redirects to another action that already exists.
    def deleteAllSampleAjax() {

		def listSampleIds= JSON.parse(params.sampleIdsList)
		def runId= params.runId
		try {
			listSampleIds.each {
				def sample = Sample.get(it.toLong())
				sampleService.delete(sample)
				}
			flash.message = "Selected samples deleted!"
		}
		catch(SequenceRunException e) {
			flash.message = e.message
		}
		redirect(action: "show", params: [id: runId])
	}
}
