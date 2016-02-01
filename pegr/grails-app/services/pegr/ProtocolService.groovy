package pegr

import grails.transaction.Transactional

class ProtocolException extends RuntimeException {
    String message
}

class ProtocolService {
    def springSecurityService
    
    @Transactional
    void save(Protocol protocol, List itemTypeIds) {
        if(protocol.save(flush: true)) {
            def changes = [:]
            def oldItemTypeIds = ProtocolItemTypes.where{protocol == protocol}.collect{it.itemType.id}
            oldItemTypeIds.each {
                changes[it] = -1                
            }
            itemTypeIds.each {
                if (changes.containsKey(it)) {
                    changes[it] = 0
                } else {
                    changes[it] = 1
                }
            }
            changes.each{ key, value ->
                if (value == -1) {
                    ProtocolItemTypes.where{protocol == protocol && itemType.id == key}.get(max: 1).delete()
                }else if (value == 1){
                    def newItemType = ItemType.get(key)
                    new ProtocolItemTypes(protocol:protocol, itemType: newItemType).save()
                }
            }
        }else {
            throw new ProtocolException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void delete(Long id) {
        try {
            ProtocolItemTypes.executeUpdate("delete ProtocolItemTypes t where t.protocol.id = :protocolId", [protocolId: id])
            Protocol.executeUpdate("delete Protocol t where t.id = :protocolId", [protocolId: id])
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolException(message: "Error deleting the protocol!")
        }
    }
        
                                        
    @Transactional
    ProtocolInstance createInstanceForSample(String protocolId, String sampleId, String priorProtInstId) {
        def user = springSecurityService.currentUser
		def priorProtInst = null
		if (priorProtInstId) {
			priorProtInst = ProtocolInstance.get(priorProtInstId)
		}
		def protocol = Protocol.get(protocolId)
		def protocolInstance = new ProtocolInstance(protocol: protocol, 
			user: user,
			prior: priorProtInst,
            completed: false)
        try {
            if (protocolInstance.save(flush: true)) {
                // update the latest protocol instance for sample
                def sample = Sample.get(sampleId)
                sample.latestProtocolInstance = protocolInstance
                sample.save(flush: true)
                return protocolInstance
            }else {
                throw new ProtocolException()
            }
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolException(message: "Error creating the new protocol instance!")
        }
    }
    
}