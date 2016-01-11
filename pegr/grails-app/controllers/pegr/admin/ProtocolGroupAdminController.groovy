package pegr.admin
import pegr.AdminCategory
import pegr.ProtocolGroup
import grails.transaction.Transactional

class ProtocolGroupAdminController {

    static scaffold = ProtocolGroup
	public static AdminCategory category = AdminCategory.PROTOCOLS
    
    @Transactional
    def update() {
        withForm {
            def protocolGroupInstance = ProtocolGroup.get(params.id)
            
            if (protocolGroupInstance) {
                //remove old protocols
                protocolGroupInstance.protocols.clear()
                
                // update with new input
                protocolGroupInstance.properties = params
                try {
                    if (protocolGroupInstance.save(flush: true)) {
                        flash.message = "Protocol Group ${protocolGroupInstance.name} has been updated!"
                        redirect(id: protocolGroupInstance.id, action: 'show')
                    } else {
                        flash.message = "Invalid inputs!"
                        redirect(id: protocolGroupInstance.id, action: 'edit', params:[protocolGroupInstance: protocolGroupInstance])
                    }
                } catch(org.springframework.dao.OptimisticLockingFailureException e) {
                    flash.message = "Oops! Please try again!"
                    redirect(id: protocolGroupInstance.id, action: 'edit', params:[protocolGroupInstance: protocolGroupInstance])
                }
            } else {
                render status: 404
            }
        }
    }
}
