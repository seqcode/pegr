package pegr

import grails.transaction.Transactional

class ProtocolException extends RuntimeException {
    String message
}

@Transactional
class ProtocolService {
    def springSecurityService
    
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