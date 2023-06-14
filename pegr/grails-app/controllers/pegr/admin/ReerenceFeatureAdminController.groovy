package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.ReferenceFeature


class ReferenceFeatureAdminController {

    ReferenceFeatureService referenceFeatureService
    def utilityService
    def dataSource

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str) {
            def c = ReferenceFeature.createCriteria()
            def listParams = [
                    max: max ?: 25,
                    sort: params.sort ?: "id",
                    order: params.order ?: "desc",
                    offset: params.offset
                ]
            def likeStr = "%" + str + "%"
            def items = c.list(listParams) {
                or {
                    ilike "filename", likeStr
                    genome {
                        ilike "name", likeStr
                    }
                    ilike "summary", likeStr
                    ilike "url", likeStr
                }
            }
            respond items, model:[referenceFeatureCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ReferenceFeature.list(params), model:[referenceFeatureCount: ReferenceFeature.count()]
        }
    }

    def show(Long id) {
        respond referenceFeatureService.get(id)
    }

    def create() {
        respond new ReferenceFeature(params)
    }

    def save(ReferenceFeature referenceFeature) {
        if (referenceFeature == null) {
            notFound()
            return
        }

        try {
            referenceFeatureService.save(referenceFeature)
        } catch (ValidationException e) {
            respond referenceFeature.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'referenceFeature.label', default: 'ReferenceFeature'), referenceFeature.id])
                redirect action: "show", id: referenceFeature.id
            }
            '*' { respond referenceFeature, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond referenceFeatureService.get(id)
    }

    def update(ReferenceFeature referenceFeature) {
        if (referenceFeature == null) {
            notFound()
            return
        }

        try {
            referenceFeatureService.save(referenceFeature)
        } catch (ValidationException e) {
            respond referenceFeature.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'referenceFeature.label', default: 'ReferenceFeature'), referenceFeature.id])
                redirect action: "show", id: referenceFeature.id
            }
            '*'{ respond referenceFeature, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        referenceFeatureService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'referenceFeature.label', default: 'ReferenceFeature'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'ReferenceFeature.csv'
        def lines = ReferenceFeature.findAll().collect { [
            it.id, 
            it.filename?it.filename:"", 
            it.genome?it.genome:"", 
            it.summary?it.summary:"", 
            it.url?it.url:"", 
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Filename, Genome, Summary, URL\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'referenceFeature.label', default: 'ReferenceFeature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
}