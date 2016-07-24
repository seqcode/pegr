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
        if(protocol.save(flush: true)) {
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
        def filesroot = utilityService.getFilesRoot()
        return new File(filesroot, "protocols"); 
    }
    
    def getProtocolFile(Long protocolId) {
        def folder = getProtocolFolder()
        return new File(folder, "protocol${protocolId}.pdf") 
    }
    
    void uploadFile(MultipartHttpServletRequest mpr, Long protocolId, String fileField) {
        def mpf = mpr.getFile(fileField);
        String fileName = mpf.getOriginalFilename();
        String fileType = mpf.getContentType();

        if(!mpf?.empty && mpf.size < 100 * 1024 * 1024 && fileType == "application/pdf") {                
            File folder = getProtocolFolder(); 
            if (!folder.exists()) { 
                folder.mkdirs(); 
            } 
            File fileDest = getProtocolFile(protocolId) 
            mpf.transferTo(fileDest)
        }
    }
}