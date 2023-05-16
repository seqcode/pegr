package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.Tissue
import pegr.AdminCategory

class TissueAdminController {

    TissueService tissueService
    def utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && Tissue.hasProperty("name")) {
            def c = Tissue.createCriteria()
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
            respond items, model:[tissueCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond Tissue.list(params), model:[tissueCount: Tissue.count()]
        }
    }

    def show(Long id) {
        respond tissueService.get(id)
    }

    def create() {
        respond new Tissue(params)
    }

    def save(Tissue tissue) {
        if (tissue == null) {
            notFound()
            return
        }

        try {
            tissueService.save(tissue)
        } catch (ValidationException e) {
            respond tissue.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tissue.label', default: 'Tissue'), tissue.id])
                redirect action: "show", id: tissue.id
            }
            '*' { respond tissue, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond tissueService.get(id)
    }

    def update(Tissue tissue) {
        if (tissue == null) {
            notFound()
            return
        }

        try {
            tissueService.save(tissue)
        } catch (ValidationException e) {
            respond tissue.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tissue.label', default: 'Tissue'), tissue.id])
                redirect action: "show", id: tissue.id
            }
            '*'{ respond tissue, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        tissueService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tissue.label', default: 'Tissue'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tissue.label', default: 'Tissue'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def mergeTissues(String fromTissueNamesStr, String toTissueName) {
        try {
            def fromTissueNames = fromTissueNamesStr.split(",").toList()
            
            def toTissue = Tissue.findByName(toTissueName)
            if (!toTissue) {
                throw new UtilityException(message: "Tissue ${toTissueName} does not exist!")
            }
            
            fromTissueNames.each { it ->
                def fromTissueName = it.trim()
                def fromTissue = Tissue.findByName(fromTissueName)
                if(!fromTissue) {
                    throw new UtilityException(message: "Tissue ${fromTissueName} does not exist!")
                }

                utilityService.mergeRowsInDb('tissue', fromTissue.id, toTissue.id)
            }
            
            flash.message = "Tissues have been successfully merged."
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "index")
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'Tissue.csv'
        def lines = Tissue.findAll().collect { [
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
