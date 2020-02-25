package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.SequenceIndex
import pegr.AdminCategory

class SequenceIndexAdminController {

    SequenceIndexService sequenceIndexService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && SequenceIndex.hasProperty("name")) {
            def c = SequenceIndex.createCriteria()
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
            respond items, model:[sequenceIndexCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond SequenceIndex.list(params), model:[sequenceIndexCount: SequenceIndex.count()]
        }
    }

    def show(Long id) {
        respond sequenceIndexService.get(id)
    }

    def create() {
        respond new SequenceIndex(params)
    }

    def save(SequenceIndex sequenceIndex) {
        if (sequenceIndex == null) {
            notFound()
            return
        }

        try {
            sequenceIndexService.save(sequenceIndex)
        } catch (ValidationException e) {
            respond sequenceIndex.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), sequenceIndex.id])
                redirect action: "show", id: sequenceIndex.id
            }
            '*' { respond sequenceIndex, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sequenceIndexService.get(id)
    }

    def update(SequenceIndex sequenceIndex) {
        if (sequenceIndex == null) {
            notFound()
            return
        }

        try {
            sequenceIndexService.save(sequenceIndex)
        } catch (ValidationException e) {
            respond sequenceIndex.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), sequenceIndex.id])
                redirect action: "show", id: sequenceIndex.id
            }
            '*'{ respond sequenceIndex, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sequenceIndexService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sequenceIndex.label', default: 'SequenceIndex'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.PROTOCOLS
}
