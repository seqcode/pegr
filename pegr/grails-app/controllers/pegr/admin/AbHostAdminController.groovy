package pegr
import pegr.AbHost
import pegr.AdminCategory
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AbHostAdminController {

    AbHostService abHostService
    def utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * List of ab hosts. If str is not given, all ab hosts are listed; 
     * else only ab hosts whose name contains the search string are listed. 
     * @param max the maximum number of ab hosts to shoow on one page
     * @param str search string
     */
    def index(Integer max, String str) {
        if (str && AbHost.hasProperty("name")) {
            def c = AbHost.createCriteria()
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
            respond items, model:[AbHostCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond AbHost.list(params), model:[AbHostCount: AbHost.count()]
        }
    }

    def show(Long id) {
        respond abHostService.get(id)
    }

    def create() {
        respond new AbHost(params)
    }

    def save(AbHost abHost) {
        if (abHost == null) {
            notFound()
            return
        }

        try {
            abHostService.save(abHost)
        } catch (ValidationException e) {
            respond abHost.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'abHost.label', default: 'AbHost'), abHost.id])
                redirect action: "show", id: abHost.id
            }
            '*' { respond abHost, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond abHostService.get(id)
    }

    def update(AbHost abHost) {
        if (abHost == null) {
            notFound()
            return
        }

        try {
            abHostService.save(abHost)
        } catch (ValidationException e) {
            respond abHost.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'abHost.label', default: 'AbHost'), abHost.id])
                redirect action: "show", id: abHost.id
            }
            '*'{ respond abHost, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        abHostService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'abHost.label', default: 'AbHost'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'abHost.label', default: 'AbHost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def mergeAbHosts(String fromAbHostNamesStr, String toAbHostName) {
        try {
            def fromAbHostNames = fromAbHostNamesStr.split(",").toList()
            
            def toAbHost = AbHost.findByName(toAbHostName)
            if (!toAbHost) {
                throw new UtilityException(message: "Ab host ${toAbHostName} does not exist!")
            }
            
            fromAbHostNames.each { it ->
                def fromAbHostName = it.trim()
                def fromAbHost = AbHost.findByName(fromAbHostName)
                if(!fromAbHost) {
                    throw new UtilityException(message: "Ab host ${fromAbHostName} does not exist!")
                }

                utilityService.mergeRowsInDb('ab_host', fromAbHost.id, toAbHost.id)
            }
            
            flash.message = "Ab hosts have been successfully merged."
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "index")
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'AbHost.csv'
        def lines = AbHost.findAll().collect { [
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
