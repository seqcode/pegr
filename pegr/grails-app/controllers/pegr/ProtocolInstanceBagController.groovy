package pegr

class ProtocolInstanceBagController {

    def springSecurityService
    def protocolInstanceBagService

    def showInstance(Long prtclInstanceId) {
        def protocolInstance = ProtocolInstance.get(prtclInstanceId)
        if (protocolInstance) {
            [protocolInstance:protocolInstance]
        }else {
            render status: 404
        }
    }
    
    def showProtocolsInGroupAjax(Long id){
        try{
			def protocolGroup = ProtocolGroup.get(id)
            if (protocolGroup) {
                render template: 'protocolsInGroup', bean: protocolGroup
            } else {
                render "<div class='errors'>Protocol group not found.</div>"    
            }
        } catch(Exception e){
            render "<div class='errors'>Please select a protocol group.</div>"
        }
    }
    
}
