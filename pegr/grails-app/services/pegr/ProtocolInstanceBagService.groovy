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
    void addItemToBag(Long itemId, Long bagId, Boolean split){
        def bag = ProtocolInstanceBag.get(bagId)
        def item = Item.get(itemId)
        def sample = Sample.findByItem(item)
        def sampleToAdd
        try {
            // create a new sample if the item is not a traced sample or split is true
            if (!sample) {
                // find the cell source
                def csItem = item
                def cellSource = CellSource.findByItem(item)
                while(csItem && !cellSource) {
                    csItem = csItem.parent
                    cellSource = CellSource.findByItem(csItem)
                }
                if (cellSource) {
                    sampleToAdd = new Sample(item: item, cellSource: cellSource)
                } else {
                    throw new ProtocolInstanceBagException(message: "No cell source found for this item!")
                }            
            } else if (split == true) {
                sampleToAdd = new Sample(item: item, cellSource: sample.cellSource)
            } else {
                sampleToAdd = sample
            }
            sampleToAdd.status = SampleStatus.PREP
            sampleToAdd.addToBags(bag).save()
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error adding this item!")
        }
    }
    
    @Transactional
    void addSubBagToBag(Long subBagId, Long bagId){
        try {
            def subBag = ProtocolInstanceBag.get(subBagId)
            def bag = ProtocolInstanceBag.get(bagId)
            subBag.tracedSamples.each { sample ->
                sample.addToBags(bag).save()
            }
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error adding this bag!")
        }
    }
    
    @Transactional
    def removeSampleFromBag(Long sampleId, Long bagId) {
        try {
            def sample = Sample.get(sampleId)
            def bag = ProtocolInstanceBag.get(bagId)
            sample.removeFromBags(bag).save(flush: true)
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
        def samples = instance.bag.tracedSamples.toList().sort {it.id}
        samples.each { sample ->
            if (!sample.item) {
                throw new ProtocolInstanceBagException(message: "sample ${sample.id} does not have an item!")
            }
            new ProtocolInstanceItems(protocolInstance: instance, item: sample.item, function: ProtocolItemFunction.CHILD).save()
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
                PoolSamples.each {
                    // remove the link between samples and bag
                    it.sample.removeFromBags(instance.bag)
                }
                // remove the link between traced samples and protocol instance
                ProtocolInstanceItems.executeUpdate("delete from ProtocolInstanceItems where protocolInstance.id = :instanceId", [instanceId: instanceId])
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
    
    Boolean readyToBeCompleted(Map sharedItemAndPoolList, Map parentsAndChildren, List samples, Protocol protocol) {
        if (sharedItemAndPoolList.any { k, v -> v.any { it.items.empty }}) {
                return false            
        } 
        if (parentsAndChildren.children) {
            if (parentsAndChildren.children.any { it == null } ) {
                return false
            }
        }
        if (protocol.addIndex) {
            if (samples.any { it.sequenceIndicesString == null || it.sequenceIndicesString == "" }) {
                return false
            }
        }
        if (protocol.addAntibody) {
            if (samples.any {it.antibody == null}) {
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
            def newSample = new Sample(cellSource: sample.cellSource, 
                                       antibody: sample.antibody, 
                                       item: item,
                                        status: SampleStatus.PREP)
            newSample.item = item
            // add the sample to the bag
            newSample.addToBags(instance.bag)
            if (!newSample.save()) {
                throw new ProtocolInstanceBagException(message: "Error creating the new sample!")
            }
        }else {
            throw new ProtocolInstanceBagException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void removeChild(Long sampleId) {
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        def item = sample.item
        def childrenCount = Item.executeQuery("select count(*) from Item where parent.id = ?", [item.parent.id])
        if (childrenCount == 1) {
            // if there is only one child
            sample.item = item.parent
            sample.save(flush: true)
        } else {
            // if there are more than one child
            sample.delete()
        }
        itemService.delete(item.id)
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
            it.addToBags(instance.bag)
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
            // remove all the samples from the bag
            def samples = bag.tracedSamples.toList()
            for (sample in samples) {
                sample.removeFromBags(bag)
            }
            
            // remove the bag itself
            bag.delete()
        } else {
            throw new ProtocolInstanceBagException(message: "Bag not found!")
        }
    }
}
