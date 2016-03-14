package pegr

import grails.transaction.Transactional

class ProtocolInstanceBagException extends RuntimeException {
    String message
}

class ProtocolInstanceBagService {
    def springSecurityService
    def itemService
    
    @Transactional
    ProtocolInstanceBag savePrtclInstBag(Long protocolGroupId, String name, Date startTime) {
        def protocolGroup = ProtocolGroup.get(protocolGroupId)
        if(protocolGroup == null) {
            throw new ProtocolInstanceBagException(message: "protocol Group not found!")
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
    void addItemToBag(Long itemId, Long bagId){
        def bag = ProtocolInstanceBag.get(bagId)
        def item = Item.get(itemId)
        def sample = Sample.findByItem(item)
        if (sample in bag.tracedSamples) {
            throw new ProtocolInstanceBagException(message: "This sample is already in the bag!")
        }
        // create a new sample if the item is not a traced sample
        if (!sample) {
            // find the cell source
            def csItem = item
            def cellSource = CellSource.findByItem(item)
            while(csItem && !cellSource) {
                csItem = csItem.parent
                cellSource = CellSource.findByItem(csItem)
            }
            if (cellSource) {
                sample = new Sample(item: item, cellSource: cellSource, status: SampleStatus.CREATED)
            } else {
                throw new ProtocolInstanceBagException(message: "No cell source found for this item!")
            }            
        }
        try {
            sample.addToBags(bag).save()
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
            
            subBag.tracedSamples.each {
                it.addToBags(bag).save()
            }
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error adding this bag!")
        }
    }
    
    @Transactional
    def removeItemFromBag(Long itemId, Long bagId) {
        try {
            def sample = Sample.where{item.id == itemId}.find()
            def bag = ProtocolInstanceBag.get(bagId)
            sample.removeFromBags(bag).save(flush: true)
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException(message: "Error removing this item!")
        }
        
    }
    
    @Transactional
    void startInstance(Long id) {
        try {
            def instance = ProtocolInstance.get(id)
            instance.user = springSecurityService.currentUser
            instance.status = ProtocolStatus.PROCESSING
            instance.startTime = new Date()
            instance.save(flush: true)
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new ProtocolInstanceBagException()
        }
    }
    
    @Transactional
    void addItemToInstance(Long itemId, Long instanceId){
        try {
            def item = Item.get(itemId)
            def instance = ProtocolInstance.get(instanceId)
            def instanceItem = new ProtocolInstanceItems(item: item, protocolInstance: instance)
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
        if (item.save(flush: true)) { 
            // add the item to the instance
            def instance = ProtocolInstance.get(instanceId)
            def instanceItem = new ProtocolInstanceItems(item: item, protocolInstance: instance)
            instanceItem.save(flush: true)
            // add all the samples in the bag to the pool
            if (item.type.category == ItemTypeCategory.SAMPLE_POOL) {
                instance.bag.tracedSamples.each {
                    new PoolSamples(pool: item, sample: it).save()
                }
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
            protocolItem.delete(flush: true)
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
    void addIndex(List sampleId, List indexIds) {
        def newSampleIndices = []
        def indexIdSet = []
        def toDelete = []
        sampleId.eachWithIndex { id, idx ->
            def sample = Sample.get(Long.parseLong(id))
            if (!sample) {
                throw new ProtocolInstanceBagException(message: "Sample not found!")
            }
            toDelete.addAll(SampleSequenceIndices.findAllBySample(sample))
            if (indexIds[idx] != "") {
                def indexStrings = indexIds[idx].split(",")*.trim()
                indexStrings.each{ 
                    def indexId = Long.parseLong(it)
                    if (indexId in indexIdSet) {
                        throw new ProtocolInstanceBagException(message: "Index ${indexId} has been used for more than twice!")
                    }
                    def index = SequenceIndex.findByIndexIdAndStatus(indexId, DictionaryStatus.Y)
                    if (!index) {
                        throw new ProtocolInstanceBagException(message: "Index ${indexId} not found!")
                    }          
                    indexIdSet.push(indexId)
                    def oldIndex = toDelete.find{it.index == index && it.sample == sample}
                    if (oldIndex) {
                        toDelete.remove(oldIndex)
                    } else {
                        newSampleIndices.push(new SampleSequenceIndices(sample: sample, index: index))
                    }
                }       
            }
        }
        toDelete.each {
            it.delete()
        }
        newSampleIndices.each {
            it.save()
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
            if (samples.any {it.sequenceIndices.empty}) {
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
            }else if (item.type == protocol.startPoolType) {
                result.startPool[0].items.add(item)
            } else if (item.type == protocol.endPoolType) {
                result.endPool[0].items.add(item)
            }else {
                result.sharedItemList.add([type: item.type, items: [item]])
            }
        }  
        return result
    }
    
    Map getParentsAndChildrenForCompletedInstance(List samples, ItemType startState, ItemType endState) {
        def parents = []
        def children = null
        if (startState && endState && startState != endState) {
            children = []
            samples.eachWithIndex { sample, idx ->
                def item = sample.item
                while(item && item.type != endState) {
                    item = item.parent
                }
                if (item && item.parent?.type == startState) {
                    parents[idx] = item.parent
                    children[idx] = item
                } else {
                    throw new ProtocolInstanceBagException(message: "Status of the parent does not match the protocol!")
                }
            }
        } else if (startState || endState) {
            def currentType = startState?: endState
            samples.eachWithIndex { sample, idx ->
                def item = sample.item
                while(item && item.type != currentType) {
                    item = item.parent
                }
                if (item) {
                    parents[idx] = item
                } 
            }
        } 
        return [parents: parents, children: children]
    }
    
    Map getParentsAndChildrenForProcessingInstance(List samples, ItemType startState, ItemType endState) {
        def parents = []
        def children = null
        if (startState && endState && startState != endState) {
            children = []
            samples.eachWithIndex { sample, idx ->
                def currentType = sample.item?.type
                if( currentType == startState) {
                    parents[idx] = sample.item
                    children[idx] = null
                } else if(currentType == endState) {
                    children[idx] = sample.item
                    if (sample.item.parent.type == startState) {
                        parents[idx] = sample.item.parent
                    } else {
                        throw new ProtocolInstanceBagException(message: "Status of the parent does not match the protocol!")
                    }
                } else {
                    throw new ProtocolInstanceBagException(message: "Status of the traced sample does not match the protocol!")
                }
            }
        } else if (startState || endState) {
            def requiredType = startState?: endState
            samples.eachWithIndex { sample, idx ->
                if(sample.item?.type == requiredType) {
                    parents[idx] = sample.item
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
    void addChild(Item item, Long sampleId) {
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new ProtocolInstanceBagException(message: "Sample not found!")
        }
        if (!sample.item) {
            throw new ProtocolInstanceBagException(message: "Parent not found!")
        }
        try {
            item.parent = sample.item
            item.save(flush: true)
            sample.item = item
            sample.save()
        }catch(Exception e) {
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
        sample.item = item.parent
        sample.save(flush: true)
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
        new ProtocolInstanceItems(item: item, protocolInstance: instance).save()
        item.samplesInPool.each {
            it.addToBags(instance.bag)
        }
    }
}
