package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.Histology
import pegr.AdminCategory

class HistologyAdminController {

    HistologyService histologyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Histology.hasProperty("name")) {
            def c = Histology.createCriteria()
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
            respond items, model:[histologyCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Histology.list(params), model:[histologyCount: Histology.count()]
        }
    }

    def show(Long id) {
        respond histologyService.get(id)
    }

    def create() {
        respond new Histology(params)
    }

    def save(Histology histology) {
        if (histology == null) {
            notFound()
            return
        }

        try {
            histologyService.save(histology)
        } catch (ValidationException e) {
            respond histology.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'histology.label', default: 'Histology'), histology.id])
                redirect action: "show", id: histology.id
            }
            '*' { respond histology, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond histologyService.get(id)
    }

    def update(Histology histology) {
        if (histology == null) {
            notFound()
            return
        }

        try {
            histologyService.save(histology)
        } catch (ValidationException e) {
            respond histology.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'histology.label', default: 'Histology'), histology.id])
                redirect action: "show", id: histology.id
            }
            '*'{ respond histology, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        histologyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'histology.label', default: 'Histology'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'histology.label', default: 'Histology'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
