package pegr.admin
import pegr.AdminCategory;
import pegr.Protocol

class ProtocolAdminController {

	public static AdminCategory category = AdminCategory.PROTOCOLS
    
    def protocolService
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [protocolList: Protocol.list(params), protocolCount: Protocol.count()]
    }
    
    def show(Long id) {
        def protocol = Protocol.get(id)
        
        [protocol: protocol]
    }
    
    def create() {
        if(request.method == "POST") {
            withForm{
                def protocol = new Protocol(params)
                def startItemTypeId = params.long('startItemTypeId')
                def endItemTypeId = params.long('endItemTypeId')
                def sharedItemTypeIds = params.list('sharedItemTypeIds')
                def individualItemTypeIds = params.list('individualItemTypeIds')
                try {
                    protocolService.save(protocol, startItemTypeId, endItemTypeId, sharedItemTypeIds, individualItemTypeIds)
                    flash.message = "New protocol saved!"
                    redirect(action: "show", id: protocol.id)
                }catch(ProtocolException e) {
                    request.message = e.message
                    render(view: "create", model: [protocol: protocol])
                }catch(Exception e) {
                    request.message = "Error saving this protocol!"
                    log.error "Error: ${e.message}", e
                    render(view: "create", model: [protocol: protocol])
                }
            }
        }
    }
    
    def edit() {
        def protocol = Protocol.get(params.long('id'))
        if (!protocol) {
            flash.message = "Protocol does not exist!"
            redirect(action: "index")
        }
        if(request.method == "POST") {
            withForm{
                protocol.properties = params
                def startItemTypeId = params.long('startItemTypeId', null)
                def endItemTypeId = params.long('endItemTypeId', null)
                def sharedItemTypeIds = params.list('sharedItemTypeIds')
                def individualItemTypeIds = params.list('individualItemTypeIds')
                try {
                    protocolService.save(protocol, startItemTypeId, endItemTypeId, sharedItemTypeIds, individualItemTypeIds)
                    flash.message = "Protocol update!"
                    redirect(action: "show", id: protocol.id)
                }catch(ProtocolException e) {
                    request.message = e.message
                    render(view: "edit", model: [protocol: protocol])
                }catch(Exception e) {
                    request.message = "Error updateing this protocol!"
                    log.error "Error: ${e.message}", e
                    render(view: "edit", model: [protocol: protocol])
                }
            }
        }else {
            [protocol: protocol]
        }
    }
    
    
    def delete(Long id) {
        try {
            protocolService.delete(id)
            flash.message = "Protocol deleted!"
            redirect(action: "index")
        }catch(ProtocolException e) {
            request.message = e.message
            redirect(action: "show", id: id)
        }
    }
}
