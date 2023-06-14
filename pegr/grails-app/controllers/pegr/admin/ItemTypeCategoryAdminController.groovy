package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.ItemTypeCategory

class ItemTypeCategoryAdminController {

    ItemTypeCategoryService itemTypeCategoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && ItemTypeCategory.hasProperty("name")) {
            def c = ItemTypeCategory.createCriteria()
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
            respond items, model:[itemTypeCategoryCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ItemTypeCategory.list(params), model:[itemTypeCategoryCount: ItemTypeCategory.count()]
        }
    }

    def show(Long id) {
        respond itemTypeCategoryService.get(id)
    }

    def create() {
        respond new ItemTypeCategory(params)
    }

    def save(ItemTypeCategory itemTypeCategory) {
        if (itemTypeCategory == null) {
            notFound()
            return
        }

        try {
            itemTypeCategoryService.save(itemTypeCategory)
        } catch (ValidationException e) {
            respond itemTypeCategory.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'itemTypeCategory.label', default: 'ItemTypeCategory'), itemTypeCategory.id])
                redirect action: "show", id: itemTypeCategory.id
            }
            '*' { respond itemTypeCategory, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond itemTypeCategoryService.get(id)
    }

    def update(ItemTypeCategory itemTypeCategory) {
        if (itemTypeCategory == null) {
            notFound()
            return
        }

        try {
            itemTypeCategoryService.save(itemTypeCategory)
        } catch (ValidationException e) {
            respond itemTypeCategory.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'itemTypeCategory.label', default: 'ItemTypeCategory'), itemTypeCategory.id])
                redirect action: "show", id: itemTypeCategory.id
            }
            '*'{ respond itemTypeCategory, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        itemTypeCategoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'itemTypeCategory.label', default: 'ItemTypeCategory'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'ItemTypeCategory.csv'
        def lines = ItemTypeCategory.findAll().collect { [
            it.id, 
            it.name?it.name:"", 
            it.superCategory?it.superCategory:"", 
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Name, Super Category\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemTypeCategory.label', default: 'ItemTypeCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.PROTOCOLS
}