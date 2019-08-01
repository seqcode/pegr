package pegr
import pegr.AbHost
import pegr.AdminCategory
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AbHostAdminController {

    AbHostService abHostService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond abHostService.list(params), model:[abHostCount: abHostService.count()]
    }

    def show(Long id) {
        respond abHostService.get(id)
    }

    def create() {
        respond new AbHost(params)
    }

    def save(AbHost abHost) {
        if (abHost == null) {
            notFound()
            return
        }

        try {
            abHostService.save(abHost)
        } catch (ValidationException e) {
            respond abHost.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'abHost.label', default: 'AbHost'), abHost.id])
                redirect action: "show", id: abHost.id
            }
            '*' { respond abHost, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond abHostService.get(id)
    }

    def update(AbHost abHost) {
        if (abHost == null) {
            notFound()
            return
        }

        try {
            abHostService.save(abHost)
        } catch (ValidationException e) {
            respond abHost.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'abHost.label', default: 'AbHost'), abHost.id])
                redirect action: "show", id: abHost.id
            }
            '*'{ respond abHost, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        abHostService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'abHost.label', default: 'AbHost'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'abHost.label', default: 'AbHost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
