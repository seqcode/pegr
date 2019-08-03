package pegr

import grails.transaction.Transactional

class ProtocolGroupException extends RuntimeException {
    String message
}

class ProtocolGroupService {
    
    ProtocolGroup get(Serializable id) {
        ProtocolGroup.get(id)
    }

    List<ProtocolGroup> list(Map args) {
        ProtocolGroup.list(args)
    }

    Long count() {
        ProtocolGroup.count()
    }

    def springSecurityService
    
    @Transactional
    def save(ProtocolGroup protocolGroup) {
        if (!protocolGroup.name || protocolGroup.name == "") {
            throw new ProtocolGroupException(message: "Name cannot be empty!")
        }
        if (!protocolGroup.protocols || protocolGroup.protocols.size() == 0) {
            throw new ProtocolGroupException(message: "Please select at least one protocol!")
        }
        protocolGroup.user = springSecurityService.currentUser
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