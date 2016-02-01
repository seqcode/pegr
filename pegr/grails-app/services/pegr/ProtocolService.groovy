package pegr

import grails.transaction.Transactional

class ProtocolException extends RuntimeException {
    String message
}

class ProtocolService {
    def springSecurityService
    
    List getRequiredItemTypes(Long id) {
        def requiredItemTypes = ProtocolItemTypes.where {protocol.id == id}.collect {it.itemType}
        return requiredItemTypes
    }
    
    @Transactional
    void save(Protocol protocol, List requiredItemTypes) {
        try {
            protocol.save(flush: true)
            ProtocolItemTypes.executeUpdate("delete ProtocolItemTypes c where c.protocol.id = :protocolId", [protocolId:protocol.id])
            requiredItemTypes.each{
                new ProtocolItemTypes(protocol:protocol, itemType: it).save()}
        }catch(Exception e){
            log.error "Error: ${e.message}", e
            throw new ProtocolException(message: "Error saving the protocol!")
        }
    }
    
    @Transactional
    void delete(Long id) {
        try {
            ProtocolItemTypes.executeUpdate("delete ProtocolItemTypes t where t.protocol.id == :protocolId", [protocolId: id])
            Protocol.executeUpdate("delete Protocol t where t.id == :protocolId", [protocolId: id])
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