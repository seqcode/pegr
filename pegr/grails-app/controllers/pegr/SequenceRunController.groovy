package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 

class SequenceRunController {
    def springSecurityService
    
    def sequenceRunService
    
    def csvConvertService
    
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
            if (run.status == RunStatus.COMPLETED) {
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
            flash.message = e.message
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
            flash.message = e.message
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
                redirect(action: "edit", params:[runId: runId])
            } catch(SequenceRunException e) {
                flash.message = e.message
                render(view: "editInfo", model: [run: run])
            }
        } else {
            flash.message = e.message
            redirect(action: "index")
        }
    }
    
    def searchPool(Long runId, Long typeId, String barcode) {
        if (request.method == "POST") {
            def poolItem = Item.where {type.id == typeId && barcode == barcode}.find()
            if (poolItem) {
                render(view: "previewPool", model: [runId: runId, poolItem: poolItem])
            } else {
                flash.message = "Pool not found!"
                [runId: runId]
            }
        } else {
            [runId: runId]   
        }
    }
    
    def addPool(Long poolItemId, Long runId) {
        try {
            def messages = sequenceRunService.addPool(poolItemId, runId)
            if (messages.size() > 0) {
                flash.message = messages.join("</br>")
            } else {
                flash.message = "All samples in the bag has been included!"
            }
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "edit", params: [runId: runId])
    }
    
    def removeExperiment(Long experimentId, Long runId) {
        try {
            sequenceRunService.removeExperiment(experimentId)
            flash.message = "Experiment deleted!"
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
    
    def run(Long runId) {
        try {
            sequenceRunService.run(runId)
            redirect(action: "show", id: runId)
        } catch(SequenceRunException e) {
            flash.message = e.message
            redirect(action: "edit", params: [runId: runId])
        }
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
                csvConvertService.migrate(folderName + filename, 
                                          RunStatus.PREP, 
                                          params.int("startLine"), 
                                          params.int("endLine")
                                         )
                flash.message = "CSV file uploaded!"
            } else {
                flash.message = "Only csv files are accepted!"
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error uploading the file!"
        }

        redirect(action: "index")
    }
}