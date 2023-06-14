package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Pipeline

class PipelineAdminController {

    PipelineService pipelineService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Pipeline.hasProperty("name")) {
            def c = Pipeline.createCriteria()
            def listParams = [
                    max: max ?: 25,
                    sort: params.sort ?: "id",
                    order: params.order ?: "desc",
                    offset: params.offset
                ]
            def likeStr = "%" + str + "%"
            def items = c.list(listParams) {
                or {
                    ilike "name", likeStr
                }
            }
            respond items, model:[pipelineCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Pipeline.list(params), model:[pipelineCount: Pipeline.count()]
        }
    }

    def show(Long id) {
        respond pipelineService.get(id)
    }

    def create() {
        respond new Pipeline(params)
    }

    def save(Pipeline pipeline) {
        if (pipeline == null) {
            notFound()
            return
        }

        try {
            pipelineService.save(pipeline)
        } catch (ValidationException e) {
            respond pipeline.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pipeline.label', default: 'Pipeline'), pipeline.id])
                redirect action: "show", id: pipeline.id
            }
            '*' { respond pipeline, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pipelineService.get(id)
    }

    def update(Pipeline pipeline) {
        if (pipeline == null) {
            notFound()
            return
        }

        try {
            pipelineService.save(pipeline)
        } catch (ValidationException e) {
            respond pipeline.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pipeline.label', default: 'Pipeline'), pipeline.id])
                redirect action: "show", id: pipeline.id
            }
            '*'{ respond pipeline, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pipelineService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pipeline.label', default: 'Pipeline'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'Pipeline.csv'
        def lines = Pipeline.findAll().collect { [
            it.id, 
            it.workflowId?it.workflowId:"", 
            it.name?it.name:"", 
            it.pipelineVersion?it.pipelineVersion:"", 
            it.note?it.note:"", 
            it.steps?'"'+it.steps.replace('"', '""')+'"':"",
            it.workflowUrl?it.workflowUrl:""
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Workflow ID, Name, Pipeline Version, Note, Steps, Workflow URL\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pipeline.label', default: 'Pipeline'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
}
