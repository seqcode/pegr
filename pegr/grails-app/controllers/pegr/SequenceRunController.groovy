package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 
import groovy.time.TimeCategory

class SequenceRunController {
    def springSecurityService    
    def sequenceRunService    
    def csvConvertService    
    def walleService
    
    // list incomplete runs
    def index(Integer max){
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "date"
            params.order = "desc"
        }
        def runs = SequenceRun.where { status != RunStatus.COMPLETED }.list(params)
        [runs: runs]
    }
    
    def completedRuns(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "date"
            params.order = "desc"
        }
        def runs = SequenceRun.where { status == RunStatus.COMPLETED }.list(params)
        [runs: runs]
    }
    
    def show(Integer id) {
        def run = SequenceRun.get(id)
        if (run) {
            if (run.status != RunStatus.PREP) {
                [run: run]
            } else {
                redirect(action: "edit", params:[runId: id])   
            }
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }
    
    def edit(Integer runId) {
        def run = SequenceRun.get(runId)
        if (run) {
            [run: run]
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
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
            redirect(action: "edit", params: [runId: run.id])
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
        redirect(action: "edit", params: [runId: runId])
    }
    
    def removePool(Long runId) {
        try {
            sequenceRunService.removePool(runId)
            flash.message = "The master pool has been removed!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "edit", params: [runId: runId])
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
        redirect(action: "edit", params: [runId: runId])
    }
    
    def removeExperiment(Long experimentId, Long runId) {
        try {
            sequenceRunService.removeExperiment(experimentId)
            flash.message = "Sequencing experiment deleted!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "edit", params: [runId: runId])
    }
    
    def updateGenomes(Long runId) {
        def messages = ""
        def expIds = params.list('experimentId')
        expIds.each{
            def paramsName = "genomes${it}"
            def genomeIds = params.list(paramsName)
            try {
                sequenceRunService.updateGenome(it, genomeIds)
            } catch (SequenceRunException e) {
                messages += "<p>e.message</p>"
            } 
        }
        if (messages == "") {
            messages = "All genomes added successfully!"
        }
        flash.message = messages
        redirect(action: "edit", params: [runId: runId])
    }
    
    def previewRun(Long runId) {
        def previousRun = walleService.getPreviousRun()
        def newFolders = walleService.getNewRunFolders()
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
    }
    
    def run(Long runId) {
        try {
            sequenceRunService.run(runId)
            redirect(action: "show", id: runId)
        } catch(SequenceRunException e) {
            flash.message = e.message
            redirect(action: "edit", params: [runId: runId])
        }
    }
    
    def emailMeetingSchedule() {
        def runId = Long.parseLong(params.runId)
        def meetingTime = params.meetingTime
            
        def bytes = sequenceRunService.calendarEventAsBytes(runId, meetingTime)
        sendMail {
            multipart true
            to "shaody8411@gmail.com"
            subject "[PEGR]Sequence Run ${runId} Meeting"
            body "The meeting for Sequence Run ${runId} is scheduled on ${meetingTime}."
            attachBytes "meeting.ics", "text/calendar", bytes 
        }
        flash.message = "Email sent!"
        redirect(action: "show", id: runId)
    }
    
    
    def upload() { 
    
    }
    
    def convertCsv() {
        def folderName = "files/csv/"
        try {
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;  
            def mpf = mpr.getFile("file");
            String filename = mpf.getOriginalFilename();
            if(!mpf?.empty && filename[-4..-1] == ".csv") {  
                File folder = new File(folderName); 
                if (!folder.exists()) { 
                    folder.mkdirs(); 
                } 
                File fileDest = new File(folder, filename)
                mpf.transferTo(fileDest)
                def user = springSecurityService.currentUser
                def basicCheck = true
                def messages = csvConvertService.migrate(folderName + filename, 
                                          RunStatus.PREP, 
                                          params.int("startLine"), 
                                          params.int("endLine"),
                                          basicCheck
                                         )
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
}