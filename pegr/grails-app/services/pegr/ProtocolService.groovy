package pegr

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ProtocolException extends RuntimeException {
    String message
}

class ProtocolService {
    def springSecurityService
    def utilityService
    
    @Transactional
    void save(Protocol protocol, Map protocolItemTypeIds) {
        try {
            protocol.save(flush: true, failOnError: true)
            def toDelete = ProtocolItemTypes.where{protocol == protocol}.list()
            
            def newTypes = []

            protocolItemTypeIds.each{ itemFunction, typeIds ->
                typeIds.each { id ->
                    try {
                        def itemType = ItemType.get(id)
                        if (itemType) {
                            newTypes.push(new ProtocolItemTypes(protocol: protocol, itemType: itemType, function: itemFunction))
                        }
                    } catch(Exception e) {}
                }
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
        } catch(Exception e) {
            throw new ProtocolException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void delete(Long id) {
        try {
            ProtocolItemTypes.executeUpdate("delete ProtocolItemTypes t where t.protocol.id = :protocolId", [protocolId: id])
            Protocol.executeUpdate("delete Protocol t where t.id = :protocolId", [protocolId: id])
            def file = getProtocolFile(id)
            if (file?.exists()) {
                file.delete()
            }
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolException(message: "Error deleting the protocol!")
        }
    }
    
    def getProtocolFile(Long protocolId) {
        def folder = utilityService.getFilesRoot()
        def protocol = Protocol.get(protocolId)
        def file = protocol?.file ? new File(folder, protocol?.file) : null 
        return file
    }
    
    @Transactional
    void uploadFile(MultipartHttpServletRequest mpr, Long protocolId, String fileField) {
        Long maxByte = 100 * 1024 * 1024
        List allowedFileTypes = ["application/pdf"]
        String folderName = "protocols"
        try {
            def filepath = utilityService.upload(mpr, fileField, allowedFileTypes, folderName, maxByte)
            def protocol = Protocol.get(protocolId)
            protocol.file = filepath
            protocol.save()     
        } catch (UtilityException e) {
            throw new ProtocolException(message: e.message)
        }
    }
    
    @Transactional
    void deleteFile(Long protocolId) {
        def protocol = Protocol.get(protocolId)
        def file = getProtocolFile(protocolId)
        if (file) {
            file.delete()            
            protocol.file = null
            protocol.save()
        }
    }
}