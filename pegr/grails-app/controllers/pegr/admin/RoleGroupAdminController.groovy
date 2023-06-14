package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.RoleGroup

class RoleGroupAdminController {
    RoleGroupService roleGroupService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && RoleGroup.hasProperty("name")) {
            def c = RoleGroup.createCriteria()
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
            respond items, model:[roleGroupCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond RoleGroup.list(params), model:[roleGroupCount: RoleGroup.count()]
        }
    }

    def show(Long id) {
        respond roleGroupService.get(id)
    }

    def create() {
        respond new RoleGroup(params)
    }

    def save(RoleGroup roleGroup) {
        if (roleGroup == null) {
            notFound()
            return
        }

        try {
            roleGroupService.save(roleGroup)
        } catch (ValidationException e) {
            respond roleGroup.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'roleGroup.label', default: 'RoleGroup'), roleGroup.id])
                redirect action: "show", id: roleGroup.id
            }
            '*' { respond roleGroup, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond roleGroupService.get(id)
    }

    def update(RoleGroup roleGroup) {
        if (roleGroup == null) {
            notFound()
            return
        }

        try {
            roleGroupService.save(roleGroup)
        } catch (ValidationException e) {
            respond roleGroup.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'roleGroup.label', default: 'RoleGroup'), roleGroup.id])
                redirect action: "show", id: roleGroup.id
            }
            '*'{ respond roleGroup, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        roleGroupService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'roleGroup.label', default: 'RoleGroup'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'RoleGroup.csv'
        def lines = RoleGroup.findAll().collect { [
            it.id, 
            it.name?it.name:"", 
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'roleGroup.label', default: 'RoleGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    public static AdminCategory category = AdminCategory.OTHER
}