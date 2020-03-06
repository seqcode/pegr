package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Aligner

/**
 * Controller for Aligner. This is intended for Admin only. 
 */
class AlignerAdminController {

    // inject alignerService
    AlignerService alignerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Aligner.hasProperty("name")) {
            def c = Aligner.createCriteria()
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
            respond items, model:[alignerCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Aligner.list(params), model:[alignerCount: Aligner.count()]
        }
    }

    def show(Long id) {
        respond alignerService.get(id)
    }

    def create() {
        respond new Aligner(params)
    }

    def save(Aligner aligner) {
        if (aligner == null) {
            notFound()
            return
        }

        try {
            alignerService.save(aligner)
        } catch (ValidationException e) {
            respond aligner.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'aligner.label', default: 'Aligner'), aligner.id])
                redirect action: "show", id: aligner.id
            }
            '*' { respond aligner, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond alignerService.get(id)
    }

    def update(Aligner aligner) {
        if (aligner == null) {
            notFound()
            return
        }

        try {
            alignerService.save(aligner)
        } catch (ValidationException e) {
            respond aligner.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'aligner.label', default: 'Aligner'), aligner.id])
                redirect action: "show", id: aligner.id
            }
            '*'{ respond aligner, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        alignerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'aligner.label', default: 'Aligner'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'aligner.label', default: 'Aligner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
}
