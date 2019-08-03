package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.ItemType

class ItemTypeAdminController {

    ItemTypeService itemTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && ItemType.hasProperty("name")) {
            def c = ItemType.createCriteria()
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
            respond items, model:[itemTypeCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ItemType.list(params), model:[itemTypeCount: ItemType.count()]
        }
    }

    def show(Long id) {
        respond itemTypeService.get(id)
    }

    def create() {
        respond new ItemType(params)
    }

    def save(ItemType itemType) {
        if (itemType == null) {
            notFound()
            return
        }

        try {
            itemTypeService.save(itemType)
        } catch (ValidationException e) {
            respond itemType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'itemType.label', default: 'ItemType'), itemType.id])
                redirect action: "show", id: itemType.id
            }
            '*' { respond itemType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond itemTypeService.get(id)
    }

    def update(ItemType itemType) {
        if (itemType == null) {
            notFound()
            return
        }

        try {
            itemTypeService.save(itemType)
        } catch (ValidationException e) {
            respond itemType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'itemType.label', default: 'ItemType'), itemType.id])
                redirect action: "show", id: itemType.id
            }
            '*'{ respond itemType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        itemTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'itemType.label', default: 'ItemType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemType.label', default: 'ItemType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
	public static AdminCategory category = AdminCategory.PROTOCOLS
}
