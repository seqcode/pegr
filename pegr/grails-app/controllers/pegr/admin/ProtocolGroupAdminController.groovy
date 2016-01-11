package pegr.admin
import pegr.AdminCategory
import pegr.ProtocolGroup
import grails.transaction.Transactional

class ProtocolGroupAdminController {

    static scaffold = ProtocolGroup
	public static AdminCategory category = AdminCategory.PROTOCOLS
    
    @Transactional
    def update(ProtocolGroup protocolGroupInstance, List newProtocolList) {
        withForm {
            if (protocolGroupInstance == null) {
                notFound()
                return
            }

            if (protocolGroupInstance.hasErrors()) {
                respond protocolGroupInstance.errors, view:'edit'
                return
            }

			int count = ProtocolGroup.get(protocolGroupInstance.id).protocols?.size()
            protocolGroupInstance.protocols.drop(count)
            protocolGroupInstance.save flush:true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'ProtocolGroup.label', default: 'ProtocolGroup'), protocolGroupInstance.id])
                    redirect(id: protocolGroupInstance.id, controller: 'ProtocolGroupAdmin', action: 'show')
                }
                '*'{ respond protocolGroupInstance, [status: OK] }
            }
        }
    }
}
