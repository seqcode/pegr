package pegr
import groovy.json.*
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ProtocolInstanceBagException extends RuntimeException {
    String message
}

class ProtocolInstanceBagService {
    def springSecurityService
    def itemService
    def sampleService
    def barcodeService
    def utilityService
    
    List getTracedSamples(Long bagId) {
        def protocolInstances = ProtocolInstance.where { bag.id == bagId}.list(sort: "bagIdx", order: "asc")
        if (protocolInstances.size() == 0) {
            return []
        }
        def n = protocolInstances.findIndexOf { it.status == ProtocolStatus.INACTIVE }
        def instance
        if (n == -1) {
            instance = protocolInstances.last()
        } else if ( n==0 ) {
            instance = protocolInstances.first()
        } else {
            instance = protocolInstances[n-1]
        }
        def tracedSamples = ProtocolInstanceItems.findAllByProtocolInstanceAndFunction(instance, ProtocolItemFunction.CHILD).collect { it.item }
        return tracedSamples
    }
    
    @Transactional
    ProtocolInstanceBag savePrtclInstBagByGroup(Long protocolGroupId, String name, Date startTime) {
        def protocolGroup = ProtocolGroup.get(protocolGroupId)
        if(protocolGroup == null) {
            throw new ProtocolInstanceBagException(message: "protocol Group not found!")
        }
        if (!name || name == "") {
            throw new ProtocolInstanceBagException(message: "Name cannot be empty!")
        }
        def prtclInstBag = new ProtocolInstanceBag(name: name,
                                                  status: ProtocolStatus.PROCESSING,
                                                  protocolGroup: protocolGroup,
                                                  startTime: startTime)
        try {
            prtclInstBag.save(flush: true)
            protocolGroup.protocols.eachWithIndex { it, n ->
                new ProtocolInstance(protocol: it, bag: prtclInstBag, bagIdx: n, status: ProtocolStatus.INACTIVE).save(flush: true)
            }
        } catch (Exception e) {         
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error saving this protocol instance bag!")
        }

        return prtclInstBag
    }
    
    @Transactional
    ProtocolInstanceBag savePrtclInstBagByProtocols(List protocols, String name, Date startTime) {
        if (!protocols || protocols.size() == 0) {
            throw new ProtocolInstanceBagException(message: "Please select at least one protocol!")
        }
        def prtclInstBag = new ProtocolInstanceBag(name: name,
                                                  status: ProtocolStatus.PROCESSING,
                                                  startTime: startTime)
        try {
            prtclInstBag.save(flush: true)
            protocols.eachWithIndex { it, n ->
                def protocol = Protocol.get(Long.parseLong(it))
                new ProtocolInstance(protocol: protocol, bag: prtclInstBag, bagIdx: n, status: ProtocolStatus.INACTIVE).save(flush: true)
            }
        } catch (Exception e) {         
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error saving this protocol instance bag!")
        }
        return prtclInstBag
    }
    
   /**
    * Link protocol instance bag to projects
    * @param bag protocol instance bag
    * @param projectIds a list of strings 
    */
    @Transactional
    addBagToProjects(ProtocolInstanceBag bag, List projectIds) {
        if (!bag) {
            throw new ProtocolInstanceBagException(message: "Bag not found!")
        }
        projectIds.each { idStr ->
            def id = utilityService.getLong(idStr)
            def project = Project.get(id)
            if (!project) {
                throw new ProtocolInstanceBagException(message: "Project not found!")
            }
            new ProjectBags(bag: bag, project: project).save()
        }
    }
    
    @Transactional
    void addItemsToBag(List itemIds, Long bagId){
        // find the first protocol instance instance of the bag
        def instance = ProtocolInstance.where { bag.id == bagId && bagIdx == 0 }.get(max: 1)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        itemIds.each { itemId ->
            // find the item
            def item = Item.get(itemId)
            if (!item) {
                throw new ProtocolInstanceBagException(message: "Item not found!")
            }
            // add the traced sample to the bag
            importItemToBag(item, bagId, instance)

            def sample = Sample.findByItem(item)
            // create a new sample if the item is not yet attached to a sample
            if (!sample) {  
                itemService.createSample(item)
            } else {
                // update the sample's status
                sample.status = SampleStatus.PREP 
                sample.save()
            }
        }
    }
    
    def importItemToBag(Item item, Long bagId, ProtocolInstance instance) {
        new ProtocolInstanceItems(item: item,
                                 protocolInstance: instance,
                                 function: ProtocolItemFunction.CHILD).save()
        if (item.project) {
            def bag = ProtocolInstanceBag.get(bagId)
            if (!ProjectBags.findByProjectAndBag(item.project, bag)) {
                new ProjectBags(bag: bag, project: item.project).save()
            }
        }
    }
    
    @Transactional
    void splitAndAddItemsToBag(List itemIds, Long bagId) {
        // find the first protocol instance instance of the bag
        def instance = ProtocolInstance.where { bag.id == bagId && bagIdx == 0 }.get(max: 1)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        itemIds.each { itemId ->
            // set the parent 
            def parent = Item.get(itemId)
            if (!parent) {
                throw new ProtocolInstanceBagException(message: "Parent item not found!")
            }

            // save the child
            try {
                def item = new Item(parent: parent,
                                     name: parent.name,
                                     type: parent.type,
                                     barcode: barcodeService.generateBarcode(),
                                     project: parent.project
                                    )
                itemService.save(item)
                // add the child to the bag
                importItemToBag(item, bagId, instance)
                // create a new sample
                itemService.createSample(item)
            } catch (ItemException e) {
                throw new ProtocolInstanceBagException(message: e.message)
            }
        }        
    }
    
    @Transactional
    def addSubBagToBag(Long priorInstanceId, Long bagId){
        def duplicateItems = []
        // find the first protocol instance instance of the bag
        def instance = ProtocolInstance.where { bag.id == bagId && bagIdx == 0 }.get(max: 1)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        try {
            def items = ProtocolInstanceItems.where {protocolInstance.id == priorInstanceId && function == ProtocolItemFunction.CHILD && it.item.status != ItemStatus.BAD}.collect{ it.item }
            items.each { tracedSample ->
                // check if the traced sample is already in the bag
                if (ProtocolInstanceItems.findByProtocolInstanceAndItem(instance, tracedSample)) {
                    duplicateItems.push(tracedSample.name)
                } else {
                    // add the item to the bag
                    importItemToBag(tracedSample, bagId, instance)
                }                
            }
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error adding the traced samples!")
        }
        if (duplicateItems.size() > 0) {
            throw new ProtocolInstanceBagException(message: "Traced sample(s) ${duplicateItems} are already included in the bag. You may choose to split and add them now or split them later inside the protocol instance.") 
        }
    }
    
    @Transactional
    def removeSampleFromBag(Long itemId, Long bagId) {
        try {
            def instances = ProtocolInstance.where {bag.id == bagId}.list()
            instances.each {
                ProtocolInstanceItems.executeUpdate("delete from ProtocolInstanceItems where protocolInstance.id=:instanceId and item.id=:itemId", [instanceId: it.id, itemId: itemId])
            }
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error removing this item!")
        }        
    }
    
    @Transactional
    void startInstance(Long id) {
        def instance = ProtocolInstance.get(id)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance ${id} not found!")
        }
        instance.user = springSecurityService.currentUser
        instance.status = ProtocolStatus.PROCESSING
        instance.startTime = new Date()
        if (!instance.save(flush: true)) {
            throw new ProtocolInstanceBagException(message: "Error updating the status of instance ${id}!")
        }
        // link traced samples to instance
        if (instance.bagIdx > 0) {
            def priorInstance = ProtocolInstance.findByBagAndBagIdx(instance.bag, instance.bagIdx - 1)
            def items = ProtocolInstanceItems.findAllByProtocolInstanceAndFunction(priorInstance, ProtocolItemFunction.CHILD)*.item
            items.each { item ->
                new ProtocolInstanceItems(protocolInstance: instance, item: item, function: ProtocolItemFunction.CHILD).save(faileOnError: true)
            }
        }
    }
    
    @Transactional
    void addItemToInstance(Long itemId, Long instanceId){
        try {
            def item = Item.get(itemId)
            def instance = ProtocolInstance.get(instanceId)
            def instanceItem = new ProtocolInstanceItems(item: item, protocolInstance: instance, function: ProtocolItemFunction.SHARED)
            instanceItem.save(flush: true)
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error adding this item!")
        }
    }
    
    @Transactional
    void saveItemInInstance(Item item, String parentTypeId, String parentBarcode, Long instanceId) {
		if (parentBarcode?.trim()) {
	        def parent = Item.findByBarcode(parentBarcode)
	        if (!parent) {
	            throw new ProtocolInstanceBagException(message: "Parent item not found!")
	        }
			item.parent = parent
		}
        // save the item
        item.user = springSecurityService.currentUser
        try {
            itemService.save(item)
        } catch (ItemException e) {
            throw new ProtocolInstanceBagException(message: e.message)
        }
        // add the item to the instance
        def instance = ProtocolInstance.get(instanceId)
        if (item.type.category.superCategory == ItemTypeSuperCategory.SAMPLE_POOL) {
            def instanceItem = new ProtocolInstanceItems(item: item, protocolInstance: instance, function: ProtocolItemFunction.END_POOL)
            instanceItem.save(flush: true)
            // add all the samples in the bag to the pool
            ProtocolInstanceItems.findAllByProtocolInstanceAndFunction(instance, ProtocolItemFunction.CHILD).each { 
                def sample = Sample.findByItem(it.item)
                new PoolSamples(pool: item, sample: sample).save()
            }
        } else {
            def instanceItem = new ProtocolInstanceItems(item: item, protocolInstance: instance, function: ProtocolItemFunction.SHARED)
            instanceItem.save(flush: true)
        }
    }
    
    @Transactional
    void removeItemFromInstance(Long itemId, Long instanceId) {
        def protocolItem = ProtocolInstanceItems.where {protocolInstance.id == instanceId && item.id == itemId}.get(max:1)
        if (!protocolItem) {
            throw new ProtocolInstanceBagException(message: "Item not found in the protocol instance!")
        }
        try {  
            // remove the item from the protocol
            protocolItem.delete(flush: true)
            // delete the item if it is the end pool in this protocol
            def instance = ProtocolInstance.get(instanceId)
            def item = Item.get(itemId)
            if (instance.protocol.endPoolType == item.type) {
                // delete the samples related to the pool
                PoolSamples.executeUpdate("delete from PoolSamples where pool.id = :itemId", [itemId: itemId])
                // delete the pool itself
                item.delete()
            } else if (instance.protocol.startPoolType == item.type) {
                // remove the link between traced samples and protocol instance
                ProtocolInstanceItems.executeUpdate("delete from ProtocolInstanceItems where function = :function and protocolInstance.id = :instanceId", [function: ProtocolItemFunction.CHILD, instanceId: instanceId])
            }
        }catch(Exception e) { 
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error removing the item!")
        }
    }
    
    @Transactional
    void completeInstance(Long instanceId) {
        def protocolInstance = ProtocolInstance.get(instanceId)
        if (!protocolInstance) {
            throw new ProtocolInstanceBagException(message: "Protocol Instance not found!")
        }
        try {
            protocolInstance.status = ProtocolStatus.COMPLETED
            protocolInstance.endTime = new Date()
            protocolInstance.save(flush:true)
        }catch(Exception e) { 
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error saving the status for protocol instance!")
        }
    }
    
    @Transactional
    void completeBag(Long bagId) {
        def protocolInstanceBag = ProtocolInstanceBag.get(bagId)
        if (!protocolInstanceBag) {
            throw new ProtocolInstanceBagException(message: "Protocol Instance Bag not found!")
        }
        try {
            protocolInstanceBag.status = ProtocolStatus.COMPLETED
            protocolInstanceBag.endTime = new Date()
            protocolInstanceBag.save(flush:true)
        }catch(Exception e) { 
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error saving the status for protocol instance bag!")
        }
    }
    
    @Transactional
    void addIndex(List itemIds, List indecies, String indexType) {
        itemIds.eachWithIndex { itemIdStr, idx ->
            def itemId = Long.parseLong(itemIdStr)
            def sample = Sample.where { item.id == itemId }.find()
            if (!sample) {
                throw new ProtocolInstanceBagException(message: "Sample not found!")
            }
            SampleSequenceIndices.executeUpdate("delete from SampleSequenceIndices where sample.id = :sampleId", [sampleId: sample.id])
            try {
                sampleService.splitAndAddIndexToSample(sample, indecies[idx])
                sampleService.copyIndexToItem(sample)
            } catch (SampleException e) {
                throw new ProtocolInstanceBagException(message: e.message)
            }
        }
    }
    
    Boolean readyToBeCompleted(Map sharedItemAndPoolList, Map parentsAndChildren, ProtocolInstance instance) {
        def protocol = instance.protocol
        if (sharedItemAndPoolList.any { k, v -> v.any { it.items.empty }}) {
                return false            
        } 
        if (parentsAndChildren.children) {
            if (parentsAndChildren.children.any { it == null } ) {
                return false
            }
        }
        if (protocol.addIndex) {
            if (parentsAndChildren.children.any { it.sequenceIndicesString == null || it.sequenceIndicesString == "" }) {
                return false
            }
        }
        if (protocol.addAntibody) {
            if (parentsAndChildren.children.any {it.antibody == null}) {
                return false
            }
        }
        def imageMap = instance.imageMap
        if (protocol.imageTypeList.any {!imageMap || !imageMap[it] || imageMap[it].size() == 0 }) {
            return false
        }
        
        return true
    }
    
    Map getSharedItemAndPoolList(Long protocolInstanceId, Protocol protocol) {
        // get the existing items
        def protocolItems = ProtocolInstanceItems.where {protocolInstance.id == protocolInstanceId}
        // shared item list
        def sharedItemList = []
        def endProductList = []
        protocol.sharedItemTypes.each { t ->
            sharedItemList.add([type: t, items: []])
        }
        protocol.endProductTypes.each { t ->
            endProductList.add([type: t, items: []])
        }
        def result = [sharedItemList: sharedItemList, endProductList: endProductList]
        if (protocol.startPoolType) {
            result.startPool = [[type: protocol.startPoolType, items:[]]]
        }
        if (protocol.endPoolType) {
            result.endPool = [[type: protocol.endPoolType, items:[]]]
        }
        // insert items to the shared item list
        protocolItems.each { protocolItem ->
            def item = protocolItem.item
            def itemsInSharedType = result.sharedItemList.find {it.type == item.type}
            def itemsInEndType = result.endProductList.find {it.type == item.type}
            if (itemsInSharedType) {
                itemsInSharedType.items.add(item)
            } else if (itemsInEndType) {
                itemsInEndType.items.add(item)
            } else if (item.type == protocol.startPoolType) {
                result.startPool[0].items.add(item)
            } else if (item.type == protocol.endPoolType) {
                result.endPool[0].items.add(item)
            } else if (protocolItem.function == ProtocolItemFunction.SHARED) {
                result.sharedItemList.add([type: item.type, items: [item]])
            } else if (protocolItem.function == ProtocolItemFunction.END_PRODUCT) {
                result.endProductList.add([type: item.type, items: [item]])
            }
        }  
        return result
    }
    
    
    Map getParentsAndChildrenForInstance(ProtocolInstance instance, ItemType startState, ItemType endState) {
        def parents = []
        def children = null
        def items = ProtocolInstanceItems.findAllByFunctionAndProtocolInstance(ProtocolItemFunction.CHILD, instance).sort {it.id}.collect { it.item }
        if (startState && endState && startState != endState) {
            children = []
            items.eachWithIndex { item, idx ->
                if( item.type == startState) {
                    parents[idx] = item
                    children[idx] = null
                } else if(item.type == endState) {
                    children[idx] = item
                    if (item.parent.type == startState) {
                        parents[idx] = item.parent
                    } else {
                        throw new ProtocolInstanceBagException(message: "Status of the parent does not match the protocol!")
                    }
                } else {
                    throw new ProtocolInstanceBagException(message: "Status of the traced sample does not match the protocol!")
                }
            }
        } else if (startState || endState) {
            def requiredType = startState?: endState
            items.eachWithIndex { item, idx ->
                if(item?.type == requiredType) {
                    parents[idx] = item
                } else {
                    throw new ProtocolInstanceBagException(message: "Status of the traced sample does not match the protocol!")
                }
            }
        }
        return [parents: parents, children: children]
    }
    
    @Transactional
    void addAntibodyToSample(Long itemId, Long antibodyId) {
        def item = Item.get(itemId)
        if (!item) {
             throw new ProtocolInstanceBagException(message: "Traced sample not found!")
        }
        def sample = Sample.findByItem(item)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        def antibody = Antibody.get(antibodyId)
        if (!antibody) {
            throw new ProtocolInstanceBagException(message: "Antibody not found!")
        }
        sample.antibody = antibody
        sample.save()
        new ItemAntibody(item: item, antibody: antibody).save()    
    }
    
    @Transactional
    void removeAntibodyFromTracedSample(Long itemId) {
        def item = Item.get(itemId)
        if (!item) {
            throw new ProtocolInstanceBagException(message: "Traced sample not found!")
        }
        ItemAntibody.findByItem(item)?.delete()
        def sample = Sample.findByItem(item) 
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        sample.antibody = null
        sample.save()
    }
    
    @Transactional
    void addChild(Item item, Long parentItemId, Long instanceId) {
        def parent = Item.get(parentItemId)
        if (!parent) {
            throw new ProtocolInstanceBagException(message: "Parent not found!")
        }
        def sample = Sample.findByItem(parent)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        
        def instance = ProtocolInstance.get(instanceId)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }

        try {
            item.parent = parent
            item.project = parent.project
            itemService.save(item)
        } catch (ItemException e) {
            throw new ProtocolInstanceBagException(message: e.message)
        }
        sample.item = item
        sample.save()
        def instanceItem = ProtocolInstanceItems.findByProtocolInstanceAndItem(instance, item.parent)
        instanceItem.item = item
        instanceItem.save()
    }
    
    @Transactional
    void addAllChildren(Long instanceId) {
        def instance = ProtocolInstance.get(instanceId)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        def parents = ProtocolInstanceItems.findAllByFunctionAndProtocolInstance(ProtocolItemFunction.CHILD, instance).sort {it.id}.collect { it.item }
        if (parents.any { it -> it.type != instance.protocol.startItemType }) {
            throw new ProtocolInstanceBagException(message: "Some traced samples may already be in the end state!")
        }
        try {
            parents.each { parent ->
                def child = new Item(parent: parent,
                                     name: parent.name,
                                     type: instance.protocol.endItemType,
                                     barcode: barcodeService.generateBarcode(),
                                     location: parent.location,
                                     project: parent.project
                                    )
                itemService.save(child)
                def sample = Sample.findByItem(parent)
                sample.item = child
                sample.save()
                def instanceItem = ProtocolInstanceItems.findByProtocolInstanceAndItem(instance, parent)
                instanceItem.item = child
                instanceItem.save()
            }
        } catch (Exception e) {
            log.error e
            throw new ProtocolInstanceBagException(message: "Error saving the children!")
        }
    }
    
    @Transactional
    void splitChildren(Item item, Long parentItemId, Long instanceId) {
        def instance = ProtocolInstance.get(instanceId)
        if (!instance?.bag) {
            throw new ProtocolInstanceBagException(message: "Protocol Instance Bag not found!")
        }
        def parent = Item.get(parentItemId)
        if (!parent) {
            throw new ProtocolInstanceBagException(message: "Parent not found!")
        }
        try {
            item.parent = parent
            item.project = parent.project
            itemService.save(item)
            // add the item to the protocol instance
            new ProtocolInstanceItems(protocolInstance: instance, item: item, function: ProtocolItemFunction.CHILD).save()
            itemService.createSample(item)
        } catch(Exception e) {
            throw new ProtocolInstanceBagException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void removeChild(Long childItemId, instanceId) {
        def child = Item.get(childItemId)
        if (child == null) {
            throw new ProtocolInstanceBagException(message: "Child item not found!")
        }
        def sample = Sample.findByItem(child)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }

        def parent = child.parent
        if (parent == null) {
            throw new ProtocolInstanceBagException(message: "Parent item not found!")
        }
        def instance = ProtocolInstance.get(instanceId)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        def instanceItem = ProtocolInstanceItems.findByProtocolInstanceAndItem(instance, child)
        if (!instanceItem) {
            throw new ProtocolInstanceBagException(message: "Item in the protocol instance not found!")
        }

        def childrenCount = ProtocolInstanceItems.where { protocolInstance.id == instanceId && item.parent != null && item.parent == parent && item.type == instance.protocol.endItemType }.count()
        if (childrenCount == 1) {
            // if there is only one child
            sample.item = parent
            sample.save(flush: true)
            instanceItem.item = parent
            instanceItem.save()            
        } else {
            // if there are more than one child
            sampleService.delete(sample)
            instanceItem.delete()
        }
        try {
            itemService.delete(child.id)
        } catch (ItemException e) {
            throw new ProtocolInstanceBagException(message: e.message)
        }
    }
    
    @Transactional
    void addPoolToInstance(Long itemId, Long instanceId) {
        def item = Item.get(itemId)
        def instance = ProtocolInstance.get(instanceId)
        if (!item) {
            throw new ProtocolInstanceBagException(message: "Pool not found!")
        }
        if (!instanceId) {
            ProtocolInstanceBagException(message: "Protocol instance not found!")
        }        
        new ProtocolInstanceItems(item: item, protocolInstance: instance, function: ProtocolItemFunction.START_POOL).save()
        item.samplesInPool.each {
            new ProtocolInstanceItems(item: it.item, protocolInstance: instance, function: ProtocolItemFunction.CHILD).save()
        }
    }
    
    @Transactional
    void updateBag(Long bagId, String name, List projectIds) {
        def bag = ProtocolInstanceBag.get(bagId)
        if (bag) {
            bag.name = name
            bag.save()
            ProjectBags.executeUpdate("delete from ProjectBags where bag.id =:bagId", [bagId: bagId])
            addBagToProjects(bag, projectIds)
        } else {
            throw new ProtocolInstanceBagException(message: "Bag not found!")
        }
    }
    
    @Transactional
    void reopenBag(Long bagId) {
        def bag = ProtocolInstanceBag.get(bagId)
        if (bag) {
            bag.status = ProtocolStatus.PROCESSING
            bag.save()
        } else {
            throw new ProtocolInstanceBagException(message: "Bag not found!")
        }
    }
    
    @Transactional
    void deleteBag(Long bagId) {
        def bag = ProtocolInstanceBag.get(bagId)
        if (bag) {
            def instances = ProtocolInstance.findAllByBag(bag)
            instances.each {
                // remove all the items from the instances
                ProtocolInstanceItems.executeUpdate('delete from ProtocolInstanceItems where protocolInstance.id = :instanceId', [instanceId: it.id])
                // delete the instances in the bag
                it.delete()
            }     
            // delete the projects related to this bag
            ProjectBags.executeUpdate("delete from ProjectBags where bag.id =:bagId", [bagId:bagId])
            // remove the bag itself
            bag.delete()
        } else {
            throw new ProtocolInstanceBagException(message: "Bag not found!")
        }
    }
    
    @Transactional
    def upload(MultipartHttpServletRequest mpr, ProtocolInstance instance, String type, String fieldName) {
        def folder = "protocolInstance"
        def maxByte = 5 * 1024 * 1024 
        def allowedFileTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif']
        def filepath = utilityService.upload(mpr, fieldName, allowedFileTypes, folder, maxByte) 
        def imageMap = utilityService.parseJson(instance.images)
        if (!imageMap) {
            imageMap = [:]
        }
        if (!imageMap[type]) {
            imageMap[type] = []
        }
        imageMap[type].push(filepath)
        instance.images = JsonOutput.toJson(imageMap)
        instance.save()      
    }
    
    @Transactional
    def removeInstanceImage(Long instanceId, String filepath) {
        def instance = ProtocolInstance.get(instanceId)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        // remove file
        def file = new File(utilityService.getFilesRoot(), filepath)
        if (file.exists()) {
            file.delete()
        }
        // update db
        def images = utilityService.parseJson(instance.images)
        images.each { k,v ->
            if (filepath in v) {
                v.remove(filepath)
            }
        }
        instance.images = JsonOutput.toJson(images)
        instance.save()
    }
}
