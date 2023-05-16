package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.IgType

class IgTypeAdminController {

    def igTypeService
    def utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && IgType.hasProperty("name")) {
            def c = IgType.createCriteria()
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
            respond items, model:[igTypeCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond IgType.list(params), model:[igTypeCount: IgType.count()]
        }
    }
    def show(Long id) {
        respond igTypeService.get(id)
    }

    def create() {
        respond new IgType(params)
    }

    def save(IgType igType) {
        if (igType == null) {
            notFound()
            return
        }

        try {
            igTypeService.save(igType)
        } catch (ValidationException e) {
            respond igType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'igType.label', default: 'IgType'), igType.id])
                redirect action: "show", id: igType.id
            }
            '*' { respond igType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond igTypeService.get(id)
    }

    def update(IgType igType) {
        if (igType == null) {
            notFound()
            return
        }

        try {
            igTypeService.save(igType)
        } catch (ValidationException e) {
            respond igType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'igType.label', default: 'IgType'), igType.id])
                redirect action: "show", id: igType.id
            }
            '*'{ respond igType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        igTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'igType.label', default: 'IgType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'igType.label', default: 'IgType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def mergeIgTypes(String fromIgTypeNamesStr, String toIgTypeName) {
        try {
            def fromIgTypeNames = fromIgTypeNamesStr.split(",").toList()
            
            def toIgType = IgType.findByName(toIgTypeName)
            if (!toIgType) {
                throw new UtilityException(message: "Ig type ${toIgTypeName} does not exist!")
            }
            
            fromIgTypeNames.each { it ->
                def fromIgTypeName = it.trim()
                def fromIgType = IgType.findByName(fromIgTypeName)
                if(!fromIgType) {
                    throw new UtilityException(message: "Ig type ${fromIgTypeName} does not exist!")
                }

                utilityService.mergeRowsInDb('ig_type', fromIgType.id, toIgType.id)
            }
            
            flash.message = "Ig types have been successfully merged."
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "index")
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'IgTypes.csv'
        def lines = IgType.findAll().collect { [
            it.id, 
            it.name?it.name:"",
            it.status?it.status:""
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Status\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    
    
	public static AdminCategory category = AdminCategory.BIO_SPECIFICATIONS
}
