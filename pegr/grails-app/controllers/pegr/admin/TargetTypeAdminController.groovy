package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.TargetType

class TargetTypeAdminController {

    TargetTypeService targetTypeService
    def utilityService

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
    
    def mergeTargetTypes(String fromTargetTypeNamesStr, String toTargetTypeName) {
        try {
            def fromTargetTypeNames = fromTargetTypeNamesStr.split(",").toList()
            
            def toTargetType = TargetType.findByName(toTargetTypeName)
            if (!toTargetType) {
                throw new UtilityException(message: "Target type ${toTargetTypeName} does not exist!")
            }
            
            fromTargetTypeNames.each { it ->
                def fromTargetTypeName = it.trim()
                def fromTargetType = TargetType.findByName(fromTargetTypeName)
                if(!fromTargetType) {
                    throw new UtilityException(message: "Target type ${fromTargetTypeName} does not exist!")
                }

                utilityService.mergeRowsInDb('target_type', fromTargetType.id, toTargetType.id)
            }
            
            flash.message = "Target types have been successfully merged."
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "index")
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'TargetType.csv'
        def lines = TargetType.findAll().collect { [
            it.id, 
            it.name?it.name:"",
            it.description?it.description:""
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Description\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
