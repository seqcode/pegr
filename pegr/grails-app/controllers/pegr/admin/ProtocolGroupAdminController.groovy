package pegr.admin
import pegr.AdminCategory
import pegr.ProtocolGroup
import pegr.ProtocolGroupException
import grails.transaction.Transactional

class ProtocolGroupAdminController {

    static scaffold = ProtocolGroup
	public static AdminCategory category = AdminCategory.PROTOCOLS
    def protocolGroupService
    
    def update() {
        withForm {
            def protocolGroup = ProtocolGroup.get(params.id)
            
            if (protocolGroup) {
                //remove old protocols
                protocolGroup.protocols.clear()
                
                // update with new input
                protocolGroup.properties = params
                try {
                    protocolGroupService.save(protocolGroup)
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
