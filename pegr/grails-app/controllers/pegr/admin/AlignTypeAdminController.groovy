package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.AlignType

class AlignTypeAdminController {

    AlignTypeService alignTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && AlignType.hasProperty("name")) {
            def c = AlignType.createCriteria()
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
            respond items, model:[alignTypeCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond AlignType.list(params), model:[alignTypeCount: AlignType.count()]
        }
    }

    def show(Long id) {
        respond alignTypeService.get(id)
    }

    def create() {
        respond new AlignType(params)
    }

    def save(AlignType alignType) {
        if (alignType == null) {
            notFound()
            return
        }

        try {
            alignTypeService.save(alignType)
        } catch (ValidationException e) {
            respond alignType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'alignType.label', default: 'AlignType'), alignType.id])
                redirect action: "show", id: alignType.id
            }
            '*' { respond alignType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond alignTypeService.get(id)
    }

    def update(AlignType alignType) {
        if (alignType == null) {
            notFound()
            return
        }

        try {
            alignTypeService.save(alignType)
        } catch (ValidationException e) {
            respond alignType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'alignType.label', default: 'AlignType'), alignType.id])
                redirect action: "show", id: alignType.id
            }
            '*'{ respond alignType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        alignTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'alignType.label', default: 'AlignType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'AlignType.csv'
        def lines = AlignType.findAll().collect { [
            it.id, 
            it.name?it.name:"", 
            it.shortName?it.shortName:""
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Short Name\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'alignType.label', default: 'AlignType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.ALIGNMENT_ANALYSIS
	
}
