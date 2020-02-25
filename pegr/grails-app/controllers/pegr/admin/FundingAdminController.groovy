package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.Funding
import pegr.AdminCategory

class FundingAdminController {

    FundingService fundingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Funding.hasProperty("name")) {
            def c = Funding.createCriteria()
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
            respond items, model:[fundingCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Funding.list(params), model:[fundingCount: Funding.count()]
        }
    }

    def show(Long id) {
        respond fundingService.get(id)
    }

    def create() {
        respond new Funding(params)
    }

    def save(Funding funding) {
        if (funding == null) {
            notFound()
            return
        }

        try {
            fundingService.save(funding)
        } catch (ValidationException e) {
            respond funding.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'funding.label', default: 'Funding'), funding.id])
                redirect action: "show", id: funding.id
            }
            '*' { respond funding, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond fundingService.get(id)
    }

    def update(Funding funding) {
        if (funding == null) {
            notFound()
            return
        }

        try {
            fundingService.save(funding)
        } catch (ValidationException e) {
            respond funding.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'funding.label', default: 'Funding'), funding.id])
                redirect action: "show", id: funding.id
            }
            '*'{ respond funding, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        fundingService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'funding.label', default: 'Funding'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'funding.label', default: 'Funding'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.OTHER
}
