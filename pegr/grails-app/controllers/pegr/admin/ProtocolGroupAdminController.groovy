package pegr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import pegr.AdminCategory
import pegr.ProtocolGroup
import pegr.ProtocolGroupException
import grails.transaction.Transactional

class ProtocolGroupAdminController {

	public static AdminCategory category = AdminCategory.PROTOCOLS
    def protocolGroupService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String str) {
        if (str && ProtocolGroup.hasProperty("name")) {
            def c = ProtocolGroup.createCriteria()
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
            respond items, model:[protocolGroupCount: items.totalCount, str: str]
        } else {       
            params.max = Math.min(max ?: 25, 100)
            respond ProtocolGroup.list(params), model:[protocolGroupCount: ProtocolGroup.count()]
        }
    }

    def show(Long id) {
        respond protocolGroupService.get(id)
    }

    def create() {
        respond new ProtocolGroup(params)
    }

    def edit(Long id) {
        respond protocolGroupService.get(id)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'protocolGroup.label', default: 'ProtocolGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def save() {
        // update with new input
        def protocolGroup = new ProtocolGroup(params)
        def protocolList = params.list("protocolList")

        try {
            protocolGroupService.save(protocolGroup, protocolList)
            flash.message = "Protocol Group ${protocolGroup.name} has been saved!"
            redirect(id: protocolGroup.id, action: 'show')
        } catch(ProtocolGroupException e) {
            flash.message = e.message
            redirect(action: 'create', params:[protocolGroupInstance: protocolGroup])
        }
    }
    
    def update() {
        withForm {
            def protocolGroup = ProtocolGroup.get(params.id)
            def protocolList = params.list("protocolList")
            if (protocolGroup) {
                // update with new input
                protocolGroup.properties = params
                try {
                    protocolGroupService.update(protocolGroup, protocolList)
                    flash.message = "Protocol Group ${protocolGroup.name} has been updated!"
                    redirect(id: protocolGroup.id, action: 'show')
                } catch(ProtocolGroupException e) {
                    flash.message = e.message
                    redirect(id: protocolGroup.id, action: 'edit', params:[protocolGroupInstance: protocolGroup])
                }
            } else {
                render status: 404
            }
        }
    }
    
    def delete(Long id) {
        try {
            protocolGroupService.delete(id)
            flash.message = "Protocol Group #${id} has been deleted!"
            redirect(action: "index")
        } catch(ProtocolGroupException e) {
            flash.message = e.message
            redirect(action: "show", id: id)
        }
    }
}
