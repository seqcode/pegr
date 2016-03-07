package pegr
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import grails.transaction.Transactional

class ItemException extends RuntimeException {
    String message
    Item item
}

class ItemService {

    def grailsApplication
    def springSecurityService

    @Transactional
    def save(Item item){
        if (Item.where{type.id == item.type.id && barcode == item.barcode && id != item.id}.get(max:1)){
            throw new ItemException(message: "This barcode has already been used!")
        } 
        // add the current user if the item is new
        if (!item.id) {
            item.user = springSecurityService.currentUser
        }
        if(!item.save(flush: true)){
            throw new ItemException(message: "Invalid inputs for item!")
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
                if (cellSource) {
                    CellSourceTreatments.executeUpdate("delete CellSourceTreatments where cellSource.id = :cellSourceId", [cellSourceId: cellSource.id])
                    cellSource.delete(flush: true)
                }
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

    def getImageFolder(Long itemId){
        File folder = new File("files/items/${itemId}"); 
    }
}
