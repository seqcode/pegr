package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.CellSourceTreatment

class CellSourceTreatmentAdminController {

    CellSourceTreatmentService cellSourceTreatmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && CellSourceTreatment.hasProperty("name")) {
            def c = CellSourceTreatment.createCriteria()
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
            respond items, model:[cellSourceTreatmentCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond CellSourceTreatment.list(params), model:[cellSourceTreatmentCount: CellSourceTreatment.count()]
        }
    }

    def show(Long id) {
        respond cellSourceTreatmentService.get(id)
    }

    def create() {
        respond new CellSourceTreatment(params)
    }

    def save(CellSourceTreatment cellSourceTreatment) {
        if (cellSourceTreatment == null) {
            notFound()
            return
        }

        try {
            cellSourceTreatmentService.save(cellSourceTreatment)
        } catch (ValidationException e) {
            respond cellSourceTreatment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cellSourceTreatment.label', default: 'CellSourceTreatment'), cellSourceTreatment.id])
                redirect action: "show", id: cellSourceTreatment.id
            }
            '*' { respond cellSourceTreatment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond cellSourceTreatmentService.get(id)
    }

    def update(CellSourceTreatment cellSourceTreatment) {
        if (cellSourceTreatment == null) {
            notFound()
            return
        }

        try {
            cellSourceTreatmentService.save(cellSourceTreatment)
        } catch (ValidationException e) {
            respond cellSourceTreatment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cellSourceTreatment.label', default: 'CellSourceTreatment'), cellSourceTreatment.id])
                redirect action: "show", id: cellSourceTreatment.id
            }
            '*'{ respond cellSourceTreatment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cellSourceTreatmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cellSourceTreatment.label', default: 'CellSourceTreatment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cellSourceTreatment.label', default: 'CellSourceTreatment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
