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
    
    def getProtocolFolder() {
        return new File("files/protocols"); 
    }
    
    def getProtocolFile(Long protocolId) {
        def folder = getProtocolFolder()
        return new File(folder, "protocol${protocolId}.pdf") 
    }
    
}