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
            def file = getProtocolFile(id)
            if (file?.exists()) {
                file.delete()
            }
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
        def protocol = Protocol.get(protocolId)
        if (protocol?.file) {
            return new File(folder, protocol?.file) 
        } else {
            return null
        }
    }
    
    @Transactional
    void uploadFile(MultipartHttpServletRequest mpr, Long protocolId, String fileField) {
        def mpf = mpr.getFile(fileField);
        String fileType = mpf.getContentType();

        if(!mpf?.empty && mpf.size < 100 * 1024 * 1024 && fileType == "application/pdf") {                
            File folder = getProtocolFolder(); 
            if (!folder.exists()) { 
                folder.mkdirs(); 
            } 

            def protocol = Protocol.get(protocolId)
            def filename =  mpf.getOriginalFilename();
            File fileDest =  new File(folder, filename)
            if (fileDest.exists()) {
                throw new ProtocolException(message: "File already exists! You may change the file name and try again.")
            }
            mpf.transferTo(fileDest)
            protocol.file = filename
            protocol.save()
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