package pegr
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import grails.transaction.Transactional

class ItemException extends RuntimeException {
    String message
    Item item
}

class ItemService {

    def utilityService
    def springSecurityService

    @Transactional
    def save(Item item){
        if (!item.type || !item.barcode || item.barcode == "") {
            throw new ItemException(message: "Item type and barcode are required!")
        }
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

    // TODO
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
        def filesroot = utilityService.getFilesRoot()
        File folder = new File(filesroot, "items/${itemId}"); 
    }
    
    def findCellSource(Item item) {
        // find the cell source
        def csItem = item
        def cellSource = CellSource.findByItem(item)
        while(csItem && !cellSource) {
            csItem = csItem.parent
            cellSource = CellSource.findByItem(csItem)
        }
        return cellSource
    }
    
    // TODO
    def clone(Item parent, Item child) {
        child.parent = parent
        save(child)
    }
    
    def createSample(Item item) {
        def cellSource = itemService.findCellSource(parent)
        new Sample(item: item, cellSource: cellSource, status: SampleStatus.PREP).save()  
    }

}
