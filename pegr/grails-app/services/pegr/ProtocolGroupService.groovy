package pegr

import grails.transaction.Transactional

class ProtocolGroupException extends RuntimeException {
    String message
}

class ProtocolGroupService {
    
    @Transactional
    def save(ProtocolGroup protocolGroup) {
        if (!protocolGroup.save(flush:true)) {
            throw new ProtocolGroupException(message: "Error saving the protocol group!")
        } 
    }
    
    @Transactional
    def delete(Long id) {
        def bag = ProtocolInstanceBag.where { protocolGroup.id == id }.find()
        if (bag) {
            throw new ProtocolGroupException(message: "The protoocol group has been used in protocol instance bags and cannot be delete!")
        } else {
            try {
                ProtocolGroup.executeUpdate("delete ProtocolGroup t where t.id = :groupId", [groupId: id])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                throw new ProtocolGroupException(message: "Error deleting the protocol group!")
            }
        }
    }
}