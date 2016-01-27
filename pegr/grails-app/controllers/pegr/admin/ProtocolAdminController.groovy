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
        def requiredItemTypes = protocolService.getRequiredItemTypes(id)
        [protocol: protocol, requiredItemTypes:requiredItemTypes]
    }
    
    def create() {
        if(request.method == "POST") {
            withForm{
                def protocol = new Protocol(params)
                try {
                    protocolService.save(protocol, params.list('requiredItemTypes'))
                    flash.message = "New protocol saved!"
                    redirect(action: "show", id: protocol.id)
                }catch(ProtocolException e) {
                    request.message = e.message
                    render(view: "create", model: [protocol: protocol, requiredItemTypes: params.requiredItemTypes])
                }
            }
        }
    }
    
    def edit() {
        def protocol = Protocol.get(params.id)
        if (!protocol) {
            flash.message = "Protocol does not exist!"
            redirect(action: "index")
        }
        if(request.method == "POST") {
            withForm{
                protocol.properties = params
                try {
                    protocolService.save(protocol, params.list('requiredItemTypes'))
                    flash.message = "Protocol update!"
                    redirect(action: "show", id: protocol.id)
                }catch(ProtocolException e) {
                    request.message = e.message
                    render(view: "edit", model: [protocol: protocol, requiredItemTypes: params.list('requiredItemTypes')])
                }
            }
        }else {
            def requiredItemTypes = protocolService.getRequiredItemTypes(params.long('id'))
            [protocol: protocol, requiredItemTypes:requiredItemTypes]
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
