package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Organization

class OrganizationAdminController {

    OrganizationService organizationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Organization.hasProperty("name")) {
            def c = Organization.createCriteria()
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
            [organizationInstanceList: items, organizationCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            [organizationInstanceList: Organization.list(params), organizationCount: Organization.count()]
        }
    }

    def show(Long id) {
        [organizationInstance: organizationService.get(id)]
    }

    def create() {
        [organizationInstance: new Organization(params)]
    }

    def save(Organization organization, Boolean addAddress) {
        if (organization == null) {
            notFound()
            return
        }

        try {
            if (!addAddress) {
                organization.address = null
            }
            organizationService.save(organization)
        } catch (ValidationException e) {
            respond organization.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect action: "show", id: organization.id
            }
            '*' { respond organization, [status: CREATED] }
        }
    }

    def edit(Long id) {
        [organizationInstance: organizationService.get(id)]
    }

    def update(Organization organization, Boolean addAddress) {
        if (organization == null) {
            notFound()
            return
        }

        try {
            if (!addAddress) {
                organization.address = null
            }
            organizationService.save(organization)
        } catch (ValidationException e) {
            render(view: "edit", model: [organizationInstance: organization])
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect action: "show", id: organization.id
            }
            '*'{ respond organization, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        organizationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'organization.label', default: 'Organization'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.OTHER
}
