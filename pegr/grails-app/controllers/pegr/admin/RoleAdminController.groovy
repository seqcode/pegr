package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.Role

class RoleAdminController {

    RoleService roleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Role.hasProperty("name")) {
            def c = Role.createCriteria()
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
            respond items, model:[roleCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Role.list(params), model:[roleCount: Role.count()]
        }
    }

    def show(Long id) {
        respond roleService.get(id)
    }

    def create() {
        respond new Role(params)
    }

    def save(Role role) {
        if (role == null) {
            notFound()
            return
        }

        try {
            roleService.save(role)
        } catch (ValidationException e) {
            respond role.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'role.label', default: 'Role'), role.id])
                redirect action: "show", id: role.id
            }
            '*' { respond role, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond roleService.get(id)
    }

    def update(Role role) {
        if (role == null) {
            notFound()
            return
        }

        try {
            roleService.save(role)
        } catch (ValidationException e) {
            respond role.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'role.label', default: 'Role'), role.id])
                redirect action: "show", id: role.id
            }
            '*'{ respond role, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        roleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'role.label', default: 'Role'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.OTHER
}
