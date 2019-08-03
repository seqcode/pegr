package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.ReadType

class ReadTypeAdminController {

    ReadTypeService readTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && ReadType.hasProperty("name")) {
            def c = ReadType.createCriteria()
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
            respond items, model:[readTypeCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ReadType.list(params), model:[readTypeCount: ReadType.count()]
        }
    }

    def show(Long id) {
        respond readTypeService.get(id)
    }

    def create() {
        respond new ReadType(params)
    }

    def save(ReadType readType) {
        if (readType == null) {
            notFound()
            return
        }

        try {
            readTypeService.save(readType)
        } catch (ValidationException e) {
            respond readType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'readType.label', default: 'ReadType'), readType.id])
                redirect action: "show", id: readType.id
            }
            '*' { respond readType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond readTypeService.get(id)
    }

    def update(ReadType readType) {
        if (readType == null) {
            notFound()
            return
        }

        try {
            readTypeService.save(readType)
        } catch (ValidationException e) {
            respond readType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'readType.label', default: 'ReadType'), readType.id])
                redirect action: "show", id: readType.id
            }
            '*'{ respond readType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        readTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'readType.label', default: 'ReadType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'readType.label', default: 'ReadType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
}
