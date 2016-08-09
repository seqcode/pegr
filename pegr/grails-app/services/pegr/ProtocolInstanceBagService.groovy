package pegr

import grails.transaction.Transactional

class ProtocolInstanceBagException extends RuntimeException {
    String message
}

class ProtocolInstanceBagService {
    def springSecurityService
    def itemService
    def sampleService
    
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
    
    @Transactional
    void addItemToBag(Long itemId, Long bagId){
        // find the first protocol instance instance of the bag
        def instance = ProtocolInstance.where { bag.id == bagId && bagIdx == 0 }.get(max: 1)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        // find the item
        def item = Item.get(itemId)
        if (!item) {
            throw new ProtocolInstanceBagException(message: "Item not found!")
        }
        // check if the traced sample is already in the bag
        if (ProtocolInstanceItems.findByProtocolInstanceAndItem(instance, item)) {
            throw new ProtocolInstanceBagException(message: "The sample is already inside the bag! You may choose to split and add the sample now or split it later inside the protocol instance.")
        }
        // add the traced sample to the first protocol instance of the bag
        new ProtocolInstanceItems(item: item,
                                 protocolInstance: instance,
                                 function: ProtoclItemFunction.CHILD).save()
        def sample = Sample.findByItem(item)
        // create a new sample if the item is not yet attached to a sample
        if (!sample) {            
            def cellSource = itemService.findCellSource(item)
            if (cellSource) {
                sample = new Sample(item: item, cellSource: cellSource)
            } else {
                throw new ProtocolInstanceBagException(message: "No cell source found for this item!")
            }            
        } 
        // update the sample's status
        sample.status = SampleStatus.PREP 
        sample.save()
    }
    
    @Transactional
    void splitAndAddItemToBag(Long itemId, Long bagId, Item item) {
        // find the first protocol instance instance of the bag
        def instance = ProtocolInstance.where { bag.id == bagId && bagIdx == 0 }.get(max: 1)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        // set the parent 
        def parent = Item.get(itemId)
        if (!parent) {
            throw new ProtocolInstanceBagException(message: "Parent item not found!")
        }
        item.parent = parent
        // save the child
        try {
            itemService.save(item)
        } catch (ItemException e) {
            throw new ProtocolInstanceBagException(message: e.message)
        }
        // add the child to the first protocol instance of the bag
         new ProtocolInstanceItems(item: item,
                                 protocolInstance: instance,
                                 function: ProtoclItemFunction.CHILD).save()
        // create a new sample
        def cellSource = itemService.findCellSource(parent)
        new Sample(item: item, cellSource: cellSource, status: SampleStatus.PREP).save()     
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
            def items = ProtocolInstanceItems.where {protocolInstance.id == priorInstanceId && function == ProtocolItemFunction.CHILD}.list()
            items.each { tracedSample ->
                // check if the traced sample is already in the bag
                if (ProtocolInstanceItems.findByProtocolInstanceAndItem(instance, item)) {
                    duplicateItems.push(tracedSample.name)
                } else {
                    // add the item to the first protocol instance of the bag
                    new ProtocolInstanceItems(protocolInstance: instance,
                                          item: tracedSample,
                                         function: ProtocolItemFunction.CHILD).save()
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
            ProtocolInstanceItems.executeUpdate("delete from ProtocolInstanceItems where protocolInstance.bag.id=:bagId and item.id=:itemId", [bagId: bagId, itemId: itemId])
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
            def items = ProtocolInstanceItems.findAllByProtocolInstanceAndFunction(priorInstance, ProtocolItemFunction.CHILD)
            items.each {
                new ProtocolInstanceItems(protocolInstance: instance, item: item, function: ProtocolItemFunction.CHILD).save()
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
			def typeId = Long.parseLong(parentTypeId)
	        def parent = Item.where{type.id == typeId && barcode == parentBarcode}.get(max: 1)
	        if (!parent) {
	            throw new ProtocolInstanceBagException(message: "Parent item not found!")
	        }
			item.parent = parent
		}
        // save the item
        item.user = springSecurityService.currentUser
        if (item.save(flush: true)) { 
            // add the item to the instance
            def instance = ProtocolInstance.get(instanceId)
            if (item.type.category == ItemTypeCategory.SAMPLE_POOL) {
                def instanceItem = new ProtocolInstanceItems(item: item, protocolInstance: instance, function: ProtocolItemFunction.END_POOL)
                instanceItem.save(flush: true)
                // add all the samples in the bag to the pool
                instance.bag.tracedSamples.each {
                    new PoolSamples(pool: item, sample: it).save()
                }
            } else {
                def instanceItem = new ProtocolInstanceItems(item: item, protocolInstance: instance, function: ProtocolItemFunction.SHARED)
                instanceItem.save(flush: true)
            }
            
        } else { 
            throw new ProtocolInstanceBagException(message: "Error saving this item!")
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
    void addIndex(List sampleIds, List indecies, String indexType) {
        sampleIds.eachWithIndex { sampleIdStr, idx ->
            def sampleId = Long.parseLong(sampleIdStr)
            def sample = Sample.get(sampleId)
            if (!sample) {
                throw new ProtocolInstanceBagException(message: "Sample not found!")
            }
            SampleSequenceIndices.executeUpdate("delete from SampleSequenceIndices where sample.id = :sampleId", [sampleId: sampleId])
            try {
                if (indexType == "ID") {
                    sampleService.splitIdAndAddIndexToSample(sample, indecies[idx])
                } else {
                    sampleService.splitAndAddIndexToSample(sample, indecies[idx])
                }
            } catch (SampleException e) {
                 throw new ProtocolInstanceBagException(message: e.message)
            }
        }
    }
    
    Boolean readyToBeCompleted(Map sharedItemAndPoolList, Map parentsAndChildren, Protocol protocol) {
        if (sharedItemAndPoolList.any { k, v -> v.any { it.items.empty }}) {
                return false            
        } 
        if (parentsAndChildren.children) {
            if (parentsAndChildren.children.any { it == null } ) {
                return false
            }
        }
        if (protocol.addIndex) {
            if (parentsAndChildren.samples.any { it.sequenceIndicesString == null || it.sequenceIndicesString == "" }) {
                return false
            }
        }
        if (protocol.addAntibody) {
            if (parentsAndChildren.samples.any {it.antibody == null}) {
                return false
            }
        }
        return true
    }
    
    Map getSharedItemAndPoolList(Long protocolInstanceId, Protocol protocol) {
        // get the existing items
        def protocolItems = ProtocolInstanceItems.where {protocolInstance.id == protocolInstanceId}.collect {it.item}
        // shared item list
        def sharedItemList = []
        protocol.sharedItemTypes.each{ t ->
            sharedItemList.add([type: t, items: []])
        }
        def result = [sharedItemList: sharedItemList]
        if (protocol.startPoolType) {
            result.startPool = [[type: protocol.startPoolType, items:[]]]
        }
        if (protocol.endPoolType) {
            result.endPool = [[type: protocol.endPoolType, items:[]]]
        }
        // insert items to the shared item list
        protocolItems.each{ item ->
            def itemsInType = result.sharedItemList.find{it.type == item.type}
            if (itemsInType) {
                itemsInType.items.add(item)
            } else if (item.type == protocol.startPoolType) {
                result.startPool[0].items.add(item)
            } else if (item.type == protocol.endPoolType) {
                result.endPool[0].items.add(item)
            } else if (item.type.category == ItemTypeCategory.OTHER ){
                result.sharedItemList.add([type: item.type, items: [item]])
            }
        }  
        return result
    }
    
    
    Map getParentsAndChildrenForInstance(ProtocolInstance instance, ItemType startState, ItemType endState) {
        def samples = []
        def parents = []
        def children = null
        def items = ProtocolInstanceItems.findAllByFunctionAndProtocolInstance(ProtocolItemFunction.CHILD, instance).sort {it.id}.collect { it.item }
        items.each { item ->
            samples << Sample.findByItem(item)
        }
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
        return [parents: parents, children: children, samples: samples]
    }
    
    @Transactional
    void addAntibodyToSample(Long sampleId, Long antibodyId) {
        def sample = Sample.get(sampleId)
        def antibody = Antibody.get(antibodyId)
        if (sample && antibody) {
            sample.antibody = antibody
            sample.save()
        } else {
            throw new ProtocolInstanceBagException(message: "Sample or antibody not found!")
        }
    }
    
    @Transactional
    void removeAntibodyFromSample(Long sampleId) {
        def sample = Sample.get(sampleId) 
        if (sample) {
            sample.antibody = null
            sample.save()
        } 
    }
    
    @Transactional
    void addChild(Item item, Long sampleId, Long instanceId) {
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        if (!sample.item) {
            throw new ProtocolInstanceBagException(message: "Parent not found!")
        }
        def instance = ProtocolInstance.get(instanceId)
        if (!instance) {
            throw new ProtocolInstanceBagException(message: "Protocol instance not found!")
        }
        item.parent = sample.item
        item.user = springSecurityService.currentUser
        if (item.save(flush: true)){
            sample.item = item
            sample.save()
            def instanceItem = ProtocolInstanceItems.findByProtocolInstanceAndItem(instance, item.parent)
            instanceItem.item = item
            instanceItem.save()
        } else {
            throw new ProtocolInstanceBagException(message: "Invalid inputs! Barcode might already exist for this type!")
        }
    }
    
    @Transactional
    void splitChildren(Item item, Long sampleId, Long instanceId) {
        def instance = ProtocolInstance.get(instanceId)
        if (!instance?.bag) {
            throw new ProtocolInstanceBagException(message: "Protocol Instance Bag not found!")
        }
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        if (!sample.item?.parent) {
            throw new ProtocolInstanceBagException(message: "Parent not found!")
        }
        item.parent = sample.item.parent
        item.user = springSecurityService.currentUser
        if (item.save()){
            // add the item to the protocol instance
            new ProtocolInstanceItems(protocolInstance: instance, item: item, function: ProtocolItemFunction.CHILD).save()
            def newSample = new Sample(sample)
            newSample.item = item
            if (!newSample.save()) {
                throw new ProtocolInstanceBagException(message: "Error creating the new sample!")
            }
        }else {
            throw new ProtocolInstanceBagException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void removeChild(Long sampleId, instanceId) {
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        def child = sample.item
        if (child == null) {
            throw new ProtocolInstanceBagException(message: "Child item not found!")
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
            sample.delete()
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
    void updateBag(Long bagId, String name) {
        def bag = ProtocolInstanceBag.get(bagId)
        if (bag) {
            bag.name = name
            bag.save()
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
                ProtocolInstanceItems.executeUpdate('delete ProtocolInstanceItems where protocolInstance.id = :instanceId', [instanceId: it.id])
                // delete the instances in the bag
                it.delete()
            }            
            // remove the bag itself
            bag.delete()
        } else {
            throw new ProtocolInstanceBagException(message: "Bag not found!")
        }
    }
}
