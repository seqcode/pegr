package pegr
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import grails.transaction.Transactional
import groovy.json.*

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
        
        if (!item.id && Item.findByBarcode(item.barcode)){
            throw new ItemException(message: "This barcode has already been used!")
        } 
        // add the current user AND status "GOOD" if the item is new
        if (!item.id) {
            item.user = springSecurityService.currentUser
            item.status = ItemStatus.GOOD
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
        switch (item.type.category.superCategory) {
            case ItemTypeSuperCategory.TRACED_SAMPLE:
                def child = Item.findByParent(item)
                if (child) {
                    throw new ItemException(message: "This traced sample cannot be deleted because it has children samples!")
                }
                def sample = Sample.findByItem(item) 
                if (sample) {
                    throw new ItemException(message: "This traced sample cannot be deleted because it has been attached to a sample!")
                }
                // delete cell source
                def cellSource = CellSource.findByItem(item)
                if (cellSource) {
                    cellSource.delete(flush: true)
                }
                // delete index
                ItemSequenceIndices.executeUpdate("delete from ItemSequenceIndices where item.id=:itemId", [itemId: id])
                // delete antibody
                ItemAntibody.executeUpdate("delete from ItemAntibody where item.id=:itemId", [itemId: id])
                break
            default: 
                def instance = ProtocolInstanceItems.findByItem(item)
                if (instance) {
                    throw new ItemException(message: "Item cannot be deleted because it has been used in protocols!")
                }
        }        
        try {          
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
        File folder = new File(filesroot, "items" + File.separator + itemId); 
    }
    
    def createSample(Item item) {
        def theItem = item
        def cellSource
        def antibody
        def itemIndices
        while(theItem) {            
            if (theItem.parent) {
                if (!antibody) {
                    antibody = ItemAntibody.findByItem(item)?.antibody
                }
                if (!itemIndices || itemIndices.size() == 0) {
                    itemIndices = ItemSequenceIndices.findAllByItem(item)
                }                
            } else {
                cellSource = CellSource.findByItem(item)
            }
            theItem = theItem.parent
        }
        // create sample, and attach item, cell source and antibody
        def sample = new Sample(item: item, cellSource: cellSource, antibody: antibody, status: SampleStatus.PREP)
        sample.save()
        // attach the index
        itemIndices.each { itemIndex ->
            new SampleSequenceIndices(sample: sample, index: itemIndex.index, indexInSet: itemIndex.indexInSet, setId: itemIndex.setId).save()       
        }
        // attach project
        if (item.project) {
            new ProjectSamples(project: item.project, sample: sample).save()
        }
        
        return sample
    }
    
    @Transactional
    def updateCustomizedFields(Item item, def params) {
        def map = [:]
        item.type.fieldList.each { field ->
            if (params[field] != null) {
                map[field] = params[field]
            }
        }
        item.customizedFields = JsonOutput.toJson(map)
        item.save()
    }
    
    @Transactional
    def saveProject(Long itemId, Long projectId) {
        def item = Item.get(itemId)
        def oldProjectId = item.project?.id
        if (oldProjectId == projectId) {
            return
        }

        def project = Project.get(projectId)
        item.project = project
        item.save()
        
        def sample = Sample.findByItem(item)

        if (sample) {            
            if (oldProjectId) {
                ProjectSamples.executeUpdate("delete from ProjectSamples where project.id =:oldProjectId and sample.id =:sampleId", [oldProjectId: oldProjectId, sampleId: sample.id])
            }
            if (project) {
                new ProjectSamples(project: project, sample: sample).save()
            }
        }

    }
    
    @Transactional
    def batchSave(List items) {
        items.each { itemCmd ->
            def item = Item.get(itemCmd.id)
            if (item) {
                updateCustomizedFields(item, itemCmd)
            }
        }
    }
}
