package pegr

import grails.transaction.Transactional

class ProtocolException extends RuntimeException {
    String message
}

class ProtocolService {
    def springSecurityService
    
    @Transactional
    void save(Protocol protocol, Long startItemTypeId, Long endItemTypeId, List sharedItemTypeIds, List individualItemTypeIds) {
        if(protocol.save(flush: true)) {
            def toDelete = ProtocolItemTypes.where{protocol == protocol}.list()
            
            def newTypes = []
            def startItemType = ItemType.get(startItemTypeId)
            if (startItemType) {
                newTypes.push(new ProtocolItemTypes(protocol:protocol, itemType: startItemType, function: ProtocolItemFunction.PARENT))
            }
            def endItemType = ItemType.get(endItemTypeId)
            if (endItemType) {
                newTypes.push(new ProtocolItemTypes(protocol:protocol, itemType: endItemType, function: ProtocolItemFunction.CHILD))
            }
            sharedItemTypeIds.each{
                try {
                    def sharedItemType = ItemType.get(it)
                    if (sharedItemType) {
                        newTypes.push(new ProtocolItemTypes(protocol:protocol, itemType: sharedItemType, function: ProtocolItemFunction.SHARED))
                    }
                } catch(Exception e) {}
            }
            individualItemTypeIds.each{
                try {
                    def individualItemType = ItemType.get(it)
                    if (individualItemType) {
                        newTypes.push(new ProtocolItemTypes(protocol:protocol, itemType: individualItemType, function: ProtocolItemFunction.INDIVIDUAL))
                    }
                } catch(Exception e) {}
            }

            newTypes.each { t ->
                def oldType = toDelete.find{it.itemType == t.itemType && it.function == t.function}
                if (oldType) {
                    toDelete.remove(oldType)
                } else {
                    t.save()
                }
            }
            toDelete.each { 
                it.delete()
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