package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.SequencingPlatform

class SequencingPlatformAdminController {

    SequencingPlatformService sequencingPlatformService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && SequencingPlatform.hasProperty("name")) {
            def c = SequencingPlatform.createCriteria()
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
            respond items, model:[sequencingPlatformCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond SequencingPlatform.list(params), model:[sequencingPlatformCount: SequencingPlatform.count()]
        }
    }

    def show(Long id) {
        respond sequencingPlatformService.get(id)
    }

    def create() {
        respond new SequencingPlatform(params)
    }

    def save(SequencingPlatform sequencingPlatform) {
        if (sequencingPlatform == null) {
            notFound()
            return
        }

        try {
            sequencingPlatformService.save(sequencingPlatform)
        } catch (ValidationException e) {
            respond sequencingPlatform.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sequencingPlatform.label', default: 'SequencingPlatform'), sequencingPlatform.id])
                redirect action: "show", id: sequencingPlatform.id
            }
            '*' { respond sequencingPlatform, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sequencingPlatformService.get(id)
    }

    def update(SequencingPlatform sequencingPlatform) {
        if (sequencingPlatform == null) {
            notFound()
            return
        }

        try {
            sequencingPlatformService.save(sequencingPlatform)
        } catch (ValidationException e) {
            respond sequencingPlatform.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sequencingPlatform.label', default: 'SequencingPlatform'), sequencingPlatform.id])
                redirect action: "show", id: sequencingPlatform.id
            }
            '*'{ respond sequencingPlatform, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sequencingPlatformService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sequencingPlatform.label', default: 'SequencingPlatform'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sequencingPlatform.label', default: 'SequencingPlatform'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
}
