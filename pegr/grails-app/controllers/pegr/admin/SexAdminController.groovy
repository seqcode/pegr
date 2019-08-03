package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Sex

class SexAdminController {

    SexService sexService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Sex.hasProperty("name")) {
            def c = Sex.createCriteria()
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
            respond items, model:[sexCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Sex.list(params), model:[sexCount: Sex.count()]
        }
    }

    def show(Long id) {
        respond sexService.get(id)
    }

    def create() {
        respond new Sex(params)
    }

    def save(Sex sex) {
        if (sex == null) {
            notFound()
            return
        }

        try {
            sexService.save(sex)
        } catch (ValidationException e) {
            respond sex.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sex.label', default: 'Sex'), sex.id])
                redirect action: "show", id: sex.id
            }
            '*' { respond sex, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sexService.get(id)
    }

    def update(Sex sex) {
        if (sex == null) {
            notFound()
            return
        }

        try {
            sexService.save(sex)
        } catch (ValidationException e) {
            respond sex.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sex.label', default: 'Sex'), sex.id])
                redirect action: "show", id: sex.id
            }
            '*'{ respond sex, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sexService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sex.label', default: 'Sex'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sex.label', default: 'Sex'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
