package pegr
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import grails.transaction.Transactional

class ItemException extends RuntimeException {
    String message
    Item item
}

class ItemService {

    def grailsApplication
    
    @Transactional
    def save(item, object){
        if(object){
            if (object.save(flush: true)) {
                item.referenceId = object.id
            }else {
                throw new ItemException(message: "Invalid inputs!")
            }
        }
        if(!item.save(flush: true)){
            throw new ItemException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    def changeBarcode(Item item, String newBarcode) {
        if (Item.where{type.id == item.type.id && barcode == newBarcode}.get(max:1)){
            throw new ItemException(message: "This barcode has already been used!")
        } 
        try {
            item.barcode = newBarcode
            item.save(flush: true)
        }catch(Exception e) {
            throw new ItemException(message: "Barcode cannot be saved!")
        }        
    }
    
    @Transactional
    def delete(Long id, File folder) {
        def item = Item.get(id)
        if(!item) {
            throw new ItemException(message: "Item not found!")
        }
        def instance = ProtocolInstanceItems.findByItem(item)
        if (instance || item.bags.size()) {
            throw new ItemException(message: "Item cannot be deleted because it has been used in protocols!")
        }
        
        try {
            item.delete(flush: true)
            if (folder.exists())
            folder.deleteDir()
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ItemException(message: "Item cannot be deleted!")
        }
    }
    
    def getClassFromObjectType(String type) {
        return grailsApplication.getDomainClass(grails.util.Metadata.current.getApplicationName()+ "." + type)
    }
    
    def getObjectFromItem(String type, Long referenceId) {
        try {
            def c = grailsApplication.getDomainClass(grails.util.Metadata.current.getApplicationName()+ "." + type)
            def object = c.clazz.get(referenceId)
            return object
        }catch(Exception e) {
            return null
        }
    }
}
