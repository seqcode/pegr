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
    def save(Item item){
        if (Item.where{type.id == item.type.id && barcode == item.barcode && id != item.id}.get(max:1)){
            throw new ItemException(message: "This barcode has already been used!")
        } 
        if(!item.save(flush: true)){
            throw new ItemException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    def save(item, object){
        save(item)            
        if(object){
            object.item = item 
            if (!object.save(flush: true)) {
                throw new ItemException(message: "Invalid inputs!")
            }
        }
    }

    @Transactional
    def delete(Long id) {
        def item = Item.get(id)
        if(!item) {
            throw new ItemException(message: "Item not found!")
        }
        File folder = getImageFolder(id)
        switch (item.type.category) {
            case ItemTypeCategory.TRACED_SAMPLE:
                def child = Item.findByParent(item)
                if (child) {
                    throw new ItemException(message: "This traced sample cannot be deleted because it has children samples!")
                }
                def sample = Sample.findByItem(item) 
                if (sample) {
                    throw new ItemException(message: "This traced sample cannot be deleted because it has been attached to a sample!")
                }
                break
            default: 
                def instance = ProtocolInstanceItems.findByItem(item)
                if (instance) {
                    throw new ItemException(message: "Item cannot be deleted because it has been used in protocols!")
                }
        }        
        try {
            if (item.type.category == ItemTypeCategory.TRACED_SAMPLE) {
                def cellSource = CellSource.findByItem(item)
                cellSource?.delete(flush: true)
            }            
            item.delete(flush: true)
            if (folder?.exists()) {
                folder.deleteDir()    
            }
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ItemException(message: "Item cannot be deleted!")
        }
    }
    
    def getObject(Long id, String type) {
        def c = getClassFromObjectType(type)
        return c?.clazz.get(id)
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
    
    def getImageFolder(Long itemId){
        File folder = new File("files/items/${itemId}"); 
    }
}
