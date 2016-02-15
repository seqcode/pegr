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
        if(item.save(flush: true)){
            if(object){
                object.item = item
                object.save(flush: true)
            }
        }
        else{
            throw new ItemException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    def changeBarcode(Item item, String newBarcode) {
        if (Item.where{type.id == item.type.id && barcode == newBarcode}.get(max:1)){
            throw new ItemException(message: "This barcode has already been used!")
        } 
        item.barcode = newBarcode
        item.save()       
    }
    
    @Transactional
    def updateParent(Long itemId, Long parentTypeId, String parentBarcode) {
        def item = Item.get(itemId)
        if (!item) {
            throw new ItemException(message: "Item not found!")
        }
        def parent = Item.where{type.id == parentTypeId && barcode == parentBarcode}.get(max:1)
        if (!parent) {
            throw new ItemException(message: "Parent not found!")
        }
        item.parent = parent
        item.save()
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
    
    def getObjectFromItem(Item item) {
        try {
            def c = grailsApplication.getDomainClass(grails.util.Metadata.current.getApplicationName()+ "." + item.type.objectType)
            def object = c.clazz.findByItem(item)
            return object
        }catch(Exception e) {
            return null
        }
    }
}
