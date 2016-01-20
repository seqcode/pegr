package pegr

class ProtocolInstanceBagController {

    def show(Long id) {
        def bag = ProtocolInstanceBag.get(id)
        def count = bag.protocolInstances.count{it.status == ProtocolStatus.COMPLETED}
        if (bag) {
            [bag:bag, count: count]
        }else {
            render status: 404
        }
    }
    
    def showInstance(Long prtclInstanceId) {
        def protocolInstance = ProtocolInstance.get(prtclInstanceId)
        if (protocolInstance) {
            [protocolInstance:protocolInstance]
        }else {
            render status: 404
        }
    }
}
