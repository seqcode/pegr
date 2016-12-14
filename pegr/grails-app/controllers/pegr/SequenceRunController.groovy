package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import groovy.time.TimeCategory
import groovy.json.*
import grails.converters.*

class SequenceRunController {
    def springSecurityService    
    def sequenceRunService    
    def qfileUploadService    
    def walleService
    def reportService
    def utilityService
    
    // list incomplete runs
    def index(Integer max, String str, String status){
        def c = SequenceRun.createCriteria()
        def listParams = [
                max: params.max ?: 25,
                sort: params.sort ?: "date",
                order: params.order ?: "desc",
                offset: params.offset
            ]
        def runs
        if (status) {
            runs = c.list(listParams) {
                eq "status", status
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
        [runs: runs, str: str]
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
    
    def displayImage(String filepath) {
        File image = new File(utilityService.getFilesRoot(), filepath)
        if (!image.exists()) {
            render(view: "/404")
            return
        }
        BufferedImage originalImage = ImageIO.read(image)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        def fileType = filepath.substring(filepath.lastIndexOf('.') + 1)
        ImageIO.write(originalImage, fileType, outputStream)
        byte[] imageInByte = outputStream.toByteArray()
        response.setHeader("Content-Length", imageInByte.length.toString())
        response.contentType = "image/"+fileType
        response.outputStream << imageInByte
        response.outputStream.flush()
    }
    
    def removeCohortImageAjax(Long cohortId, String filepath) {
        sequenceRunService.removeCohortImage(cohortId, filepath)
        render ""
        return
    }
}