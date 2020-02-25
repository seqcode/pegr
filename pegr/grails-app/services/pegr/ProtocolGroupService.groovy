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
    def save(ProtocolGroup protocolGroup, List protocolList) {
        if (!protocolGroup.name || protocolGroup.name == "") {
            throw new ProtocolGroupException(message: "Name cannot be empty!")
        }
        if (!protocolList || protocolList.size() == 0) {
            throw new ProtocolGroupException(message: "Please select at least one protocol!")
        }

        try {
            protocolGroup.user = springSecurityService.currentUser
            protocolGroup.save()
            
            protocolList.eachWithIndex {protocolId, index ->
                def protocol = Protocol.get(protocolId.toInteger())
                new ProtocolGroupProtocols(protocolGroup: protocolGroup,
                                           protocol: protocol,
                                           protocolsIdx: index).save()
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolGroupException(message: "Error saving the protocol group!")
        }
    }
    
    @Transactional
    def update(ProtocolGroup protocolGroup, List protocolList) {
        if (!protocolGroup.name || protocolGroup.name == "") {
            throw new ProtocolGroupException(message: "Name cannot be empty!")
        }
        if (!protocolList || protocolList.size() == 0) {
            throw new ProtocolGroupException(message: "Please select at least one protocol!")
        }

        try {
            protocolGroup.save()
            ProtocolGroupProtocols.executeUpdate("delete from ProtocolGroupProtocols where protocolGroup.id =:protocolGroupId", [protocolGroupId: protocolGroup.id])
            protocolList.eachWithIndex {protocol, index ->
                new ProtocolGroupProtocols(protocolGroup: protocolGroup,
                                           protocol: protocol,
                                           protocolsIdx: index).save()
            }
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolGroupException(message: "Error updating the protocol group!")
        }
    }
    
    @Transactional
    def delete(Long id) {
        def bag = ProtocolInstanceBag.where { protocolGroup.id == id }.find()
        if (bag) {
            throw new ProtocolGroupException(message: "The protoocol group has been used in protocol instance bags and cannot be delete!")
        } else {
            try {
                ProtocolGroupProtocols.executeUpdate("delete ProtocolGroupProtocols t where t.protocolGroup.id = :groupId", [groupId: id])
                ProtocolGroup.executeUpdate("delete ProtocolGroup t where t.id = :groupId", [groupId: id])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                throw new ProtocolGroupException(message: "Error deleting the protocol group!")
            }
        }
    }
}