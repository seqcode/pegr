package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.TargetType

class TargetTypeAdminController {

    TargetTypeService targetTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && TargetType.hasProperty("name")) {
            def c = TargetType.createCriteria()
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
            respond items, model:[TargetTypeCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond TargetType.list(params), model:[TargetTypeCount: TargetType.count()]
        }
    }

    def show(Long id) {
        respond targetTypeService.get(id)
    }

    def create() {
        respond new TargetType(params)
    }

    def save(TargetType targetType) {
        if (targetType == null) {
            notFound()
            return
        }

        try {
            targetTypeService.save(targetType)
        } catch (ValidationException e) {
            respond targetType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'targetType.label', default: 'TargetType'), targetType.id])
                redirect action: "show", id: targetType.id
            }
            '*' { respond targetType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond targetTypeService.get(id)
    }

    def update(TargetType targetType) {
        if (targetType == null) {
            notFound()
            return
        }

        try {
            targetTypeService.save(targetType)
        } catch (ValidationException e) {
            respond targetType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'targetType.label', default: 'TargetType'), targetType.id])
                redirect action: "show", id: targetType.id
            }
            '*'{ respond targetType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        targetTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'targetType.label', default: 'TargetType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'targetType.label', default: 'TargetType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
