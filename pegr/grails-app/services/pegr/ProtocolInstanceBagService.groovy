package pegr

import grails.transaction.Transactional

class ProtocolInstanceBagException extends RuntimeException {
    String message
}

class ProtocolInstanceBagService {
    def springSecurityService
    
    List fetchIncomplete(PrtclInstBagType requestedType) {
        def bags = ProtocolInstanceBag.where { type == requestedType && status != ProtocolStatus.COMPLETED }.list(sort: "lastUpdated", order: "desc")
        return bags
    }
    
    List fetchCompleted(PrtclInstBagType requestedType) {
        def bags = ProtocolInstanceBag.where { type == requestedType && status == ProtocolStatus.COMPLETED }.list(sort: "lastUpdated", order: "desc")
        return bags
    }
    
    @Transactional
    ProtocolInstanceBag savePrtclInstBag(Long protocolGroupId, PrtclInstBagType bagType) {
        def protocolGroup = ProtocolGroup.get(protocolGroupId)
        if(protocolGroup == null) {
            throw new ProtocolInstanceBagException(message: "protocol Group not found!")
        }
        def prtclInstBag = new ProtocolInstanceBag(type: bagType,
                                                  status: ProtocolStatus.INACTIVE,
                                                  protocolGroup: protocolGroup)
        protocolGroup.protocols.each {
            prtclInstBag.addToProtocolInstances(new ProtocolInstance(protocol: it,
                                                status: ProtocolStatus.INACTIVE))
        }
        try {
            prtclInstBag.save(flush: true)
            return prtclInstBag
        } catch (Exception e) {         
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error saving this protocol instance bag!")
        }
    }
}
