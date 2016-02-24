package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 

class SequenceRunController {
    def springSecurityService
    
    def sequenceRunService
    
    def csvConvertService
    
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
                redirect(action: "edit", id: id)   
            }
        } else {
            flash.message = "Sequence run not found!"
            redirect(action: "index")
        }
    }
    
    def edit(Integer id) {
        def run = SequenceRun.get(id)
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
            redirect(action: "edit", id: run.id)
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
    
    def editInfo(Long id) {
        def run = SequenceRun.get(id)
        if (run) {
            [run: run]
        } else {
            flash.message = e.message
            redirect(action: "index")
        }
    }
    
    def update(Long id) {
        def run = SequenceRun.get(id)
        if (run) {
            try {
                run.properties = params 
                sequenceRunService.save(run)
                redirect(action: "edit", id: run.id)
            } catch(SequenceRunException e) {
                flash.message = e.message
                render(view: "editInfo", model: [run: run])
            }
        } else {
            flash.message = e.message
            redirect(action: "index")
        }
    }
    
    def searchSample(Long runId) {
        [runId: runId]
    }
    
    def searchSampleById(Long runId, Long sampleId) {
        def sample = Sample.get(sampleId)
        if (sample) {
            def bag = sample.bags.size() > 0 ? sample.bags.last() : null
            render(view: "preview", model: [sample: sample, runId: runId, bag: bag])
        } else {
            flash.message = "Sample not found!"
            redirect(action: searchSample, params: [runId: runId])
        }
    }
    
    def searchSampleByBarcode(Long runId, Long typeId, String barcode) {
        def item = Item.where{type.id == typeId && barcode == barcode}.find()
        if (item) {
            def sample = Sample.findByItem(item)
            if (sample) {
                def bag = sample.bags.size() > 0 ? sample.bags.last() : null
                render(view: "preview", model: [sample: sample, runId: runId, bag: bag])
            } else {
                flash.message = "This item is not linked to a sample!"
                redirect(action: searchSample, params: [runId: runId])
            }
        } else {
            flash.message = "Item not found!"
            redirect(action: searchSample, params: [runId: runId])
        }            
    }
    
    def addSample(Long sampleId, Long runId) {
        try {
            sequenceRunService.addSample(sampleId, runId)
            flash.message = "Sample ${sampleId} included!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "edit", id: runId)
    }
    
    def addBag(Long bagId, Long runId) {
        try {
            def messages = sequenceRunService.addBag(bagId, runId)
            if (messages.size() > 0) {
                flash.message = messages.join("</br>")
            } else {
                flash.message = "All samples in the bag has been included!"
            }
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "edit", id: runId)
    }
    
    def removeExperiment(Long experimentId, Long runId) {
        try {
            sequenceRunService.removeExperiment(experimentId)
            flash.message = "Experiment deleted!"
        } catch (SequenceRunException e) {
            flash.message = e.message
        }
        redirect(action: "edit", id: runId)
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
        redirect(action: "edit", id: runId)
    }
    
    def run(Long runId) {
        try {
            sequenceRunService.run(runId)
            redirect(action: "show", id: runId)
        } catch(SequenceRunException e) {
            flash.message = e.message
            redirect(action: "edit", id: runId)
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
                                          params.int("endLine"), 
                                          user)
                flash.message = "CSV file uploaded!"
            } else {
                flash.message = "Only csv files are accepted!"
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error uploading the file!"
        }

        redirect(action: "show", id: params.itemId)
    }
}