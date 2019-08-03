package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Assay

class AssayAdminController {
     AssayService assayService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Assay.hasProperty("name")) {
            def c = Assay.createCriteria()
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
            respond items, model:[assayCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Assay.list(params), model:[assayCount: Assay.count()]
        }
    }

    def show(Long id) {
        respond assayService.get(id)
    }

    def create() {
        respond new Assay(params)
    }

    def save(Assay assay) {
        if (assay == null) {
            notFound()
            return
        }

        try {
            assayService.save(assay)
        } catch (ValidationException e) {
            respond assay.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assay.label', default: 'Assay'), assay.id])
                redirect action: "show", id: assay.id
            }
            '*' { respond assay, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond assayService.get(id)
    }

    def update(Assay assay) {
        if (assay == null) {
            notFound()
            return
        }

        try {
            assayService.save(assay)
        } catch (ValidationException e) {
            respond assay.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assay.label', default: 'Assay'), assay.id])
                redirect action: "show", id: assay.id
            }
            '*'{ respond assay, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        assayService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assay.label', default: 'Assay'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assay.label', default: 'Assay'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

	public static AdminCategory category = AdminCategory.PROTOCOLS
}