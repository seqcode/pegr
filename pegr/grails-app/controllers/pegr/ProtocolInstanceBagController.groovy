package pegr

class ProtocolInstanceBagController {

    def springSecurityService
    def protocolInstanceBagService
    
    def index() {
        redirect(action: "processingBags")
    } 
    
    def processingBags() {
        def bags = protocolInstanceBagService.fetchProcessingBags()
        [bags: bags]
    }
    
    def completedBags(){
        def bags = protocolInstanceBagService.fetchCompletedBags()
        [bags: bags]
    }
        
    def create() {
        def admin = User.get(1)
        def protocolGroups = ProtocolGroup.where { (user == admin) || (user == null) || (user == springSecurityService.currentUser)}
        [protocolGroups: protocolGroups]
    }
    
    def savePrtclInstBag() {        
        try {
            def prtclInstBag = protocolInstanceBagService.savePrtclInstBag(Long.parseLong(params.protocolGroupId), params.name, params.startTime)
            redirect(action: "showBag", id: prtclInstBag.id)
        }catch( ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "create")
        }        
    }
    
    def showBag(Long id) {
        def bag = ProtocolInstanceBag.get(id)
        def protocolInstances = ProtocolInstance.where { bag.id == id}.list(sort: "bagIdx", order: "asc")
        def count = protocolInstances.count{it.status == ProtocolStatus.COMPLETED}
        def completed = (bag.status == ProtocolStatus.COMPLETED)
        def toBeCompleted = (bag.status != ProtocolStatus.COMPLETED && protocolInstances.last().status == ProtocolStatus.COMPLETED)
        if (bag) {
            [bag:bag, count: count, protocolInstances: protocolInstances, completed: completed, toBeCompleted: toBeCompleted]
        }else {
            render status: 404
        }
    }
    
   def searchItemForBag(Long id){
        [bagId: id]       
    }
    
    def previewItemAndBag(Long typeId, String barcode, Long bagId) {
        def itemType = ItemType.get(typeId)
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
        if (item) {        
            def sample = Sample.findByItem(item)
            def subBag = null
            if (sample && !sample.bags.empty){
                subBag = sample.bags.last()
                if (subBag.status == ProtocolStatus.COMPLETED) {
                    render(view:"previewItemAndBag", model: [item: item, subBag: subBag, bagId: bagId])
                } else {
                    flash.message = "The previous protocol for this sample is not completed yet!"
                    redirect(action: "searchItemForBag", id: bagId)
                }
            } else {
                render(view:"previewItemAndBag", model: [item: item, bagId: bagId])
            }
        } else {
            flash.message = "No item found!"
            redirect(action: "searchItemForBag", id: bagId)
        }
    }
    
    def addItemToBag(Long itemId, Long bagId) {
        try {
            protocolInstanceBagService.addItemToBag(itemId, bagId)
            redirect(action: "showBag", id: bagId)
        }catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "searchItemForBag", id: bagId)
        }
    }
    
    def addSubBagToBag(Long subBagId, Long bagId) {
        try {
            protocolInstanceBagService.addSubBagToBag(subBagId, bagId)
            redirect(action: "showBag", id: bagId)
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "searchItemForBag", id: bagId)
        }
    }
    
    def removeItemFromBag(Long itemId, Long bagId) {
        try {
            protocolInstanceBagService.removeItemFromBag(itemId, bagId)
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "showBag", id: bagId)
    }
    
    def removeBagFromBag(Long subBagId, Long bagId) {        
        try {
            protocolInstanceBagService.removeBagFromBag(subBagId)
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "showBag", id: bagId)
    }

    def startInstance(Long id) {
        try {
            protocolInstanceBagService.startInstance(id)
            redirect(action: "showInstance", id: id)
        }catch(ProtocolInstanceBagException e){
            render status: 404
        }
    }
    
    def showInstance(Long id) {
        def protocolInstance = ProtocolInstance.get(id)
        if (protocolInstance) {
            def protocol = protocolInstance.protocol
            // get shared item list
            def sharedItemList = protocolInstanceBagService.getSharedItemList(id, protocol)
            // prepare the individual item table template
            def template = protocolInstanceBagService.getTemplate(protocol)
            def samples = null
            if (template) {
                // get samples in the bag
                samples = protocolInstance.bag.tracedSamples.toList().sort {it.id}
            }
            def completed = (protocolInstance.bag.status == ProtocolStatus.COMPLETED)
            if (template) {
                if (completed) {
                    template = "show" + template
                }else {
                    template = "edit" + template
                }
            }
            try{
                def toBeCompleted = false
                def results
                if (completed) {
                    results = protocolInstanceBagService.getParentsAndChildrenForCompletedInstance(samples, protocol.startItemType, protocol.endItemType)
                } else {
                    results = protocolInstanceBagService.getParentsAndChildrenForProcessingInstance(samples, protocol.startItemType, protocol.endItemType)                
                    toBeCompleted = sharedItemList.every{ !it.items.empty } && results.children.every{ it != null }
                }
                render(view: "editInstance", model: [protocolInstance: protocolInstance, 
                                                 sharedItemList: sharedItemList,
                                                 template: template,
                                                 samples: samples,
                                                 parents: results.parents,
                                                 children: results.children,
                                                 childType: protocol.endItemType,
                                                 toBeCompleted: toBeCompleted])
            } catch(ProtocolInstanceBagException e) {
                flash.message = e.message
                redirect(action: "showBag", id: protocolInstance.bag.id)
            }
        }else {
            render status: 404
        }
    }
    
    def searchItemForInstance(Long id){
        [instanceId: id]       
    }
    
    def searchItemForTypeInstance(Long instanceId, Long typeId){
        def itemType = ItemType.get(typeId)
        [instanceId: instanceId, itemType: itemType]       
    }
    
    def previewItemInInstance(Long typeId, String barcode, Long instanceId) {
        def itemType = ItemType.get(typeId)
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
        if (item) {
            render(view:"previewItemInInstance", model: [item: item, instanceId: instanceId])
        }else {
            item = new Item(type: itemType, barcode: barcode)
            render(view: "createItemInInstance", model: [instanceId: instanceId, item:item])
        }
    }
    
    def addItemToInstance(Long itemId, Long instanceId) {
        try {
            protocolInstanceBagService.addItemToInstance(itemId, instanceId)
            redirect(action: "showInstance", id: instanceId)
        }catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "searchItemForInstance", id: instanceId)
        }
    }
    
    def saveItemInInstance() {
        
        def item = new Item(params)
		def instanceId = Long.parseLong(params.instanceId)
        try {
            protocolInstanceBagService.saveItemInInstance(item, params.parentTypeId, params.parentBarcode, instanceId)
            redirect(action: "showInstance", id: instanceId)
        }catch(ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "searchItemForInstance", id: instanceId)
        }
    }
    
    def showProtocolsInGroupAjax(Long id){
        try{
			def protocolGroup = ProtocolGroup.get(id)
            if (protocolGroup) {
                render template: 'protocolsInGroup', bean: protocolGroup
            } else {
                render "<div class='errors'>Protocol group not found.</div>"    
            }
        } catch(Exception e){
            render "<div class='errors'>Please select a protocol group.</div>"
        }
    }
    
    def removeItemFromInstance(Long itemId, Long instanceId){
        try {
            protocolInstanceBagService.removeItemFromInstance(itemId, instanceId)
        }catch(ProtocolInstanceBagException e) { 
            flash.message = e.message
        }
        redirect(action: "showInstance", id: instanceId)
    }
    
    def completeInstance(Long instanceId, Long bagId) {
        try {
            protocolInstanceBagService.completeInstance(instanceId)
            redirect(action:"showBag", id: bagId)
        } catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "showInstance", id: instanceId)
        }

    }
    
    def completeBag(Long bagId) {
        try {
            protocolInstanceBagService.completeBag(bagId)
            redirect(action:"index")
        } catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "showBag", id: bagId)
        }

    }
    
    def addIndex(Long instanceId) {
        def sampleId = params.list('sampleId')
        def indexIds = params.list('indexId')
        try {
            protocolInstanceBagService.addIndex(sampleId, indexIds)
            flash.message = "Index saved!"            
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message  
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error saving the index!"
        }        
        redirect(action: "showInstance", id: instanceId)
    }
    
    def searchAntibody(Long sampleId, Long instanceId) {
        def antibodyTypeId = ItemType.findByName('Antibody')
        [instanceId: instanceId, sampleId: sampleId, antibodyTypeId: antibodyTypeId]
    }
    
    def previewAntibody(Long sampleId, Long instanceId, String barcode) {
        def item = Item.where{type.name == "Antibody" && barcode == barcode}.get(max: 1)
        if (item) {
            def antibody = Antibody.findByItem(item)
            if (antibody) {
                render(view: "previewAntibody", model: [antibody: antibody, instanceId: instanceId, sampleId: sampleId] )
            } 
        } else {
            flash.message = "Antibody not found!"
            redirect(action: "searchAntibody", params: [instanceId: instanceId, sampleId: sampleId])
        }
    }
    
    def addAntibodyToSample(Long sampleId, Long instanceId, Long antibodyId) {
        try{
            protocolInstanceBagService.addAntibodyToSample(sampleId, antibodyId)
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error adding the antibody to the sample!"
        }
        redirect(action: "showInstance", id: instanceId)
    }
    
    def removeAntibodyFromSample(Long sampleId, Long instanceId) {
        protocolInstanceBagService.removeAntibodyFromSample(sampleId)
        redirect(action: "showInstance", id: instanceId)
    }
    
    def addChild(Long sampleId, Long instanceId) {
        if (request.method == "POST") {
            withForm {
                def item = new Item(params)
                try {
                    protocolInstanceBagService.addChild(item, sampleId)
                    redirect(action: "showInstance", id: instanceId)
                }catch(ProtocolInstanceBagException e){
                    flash.message = e.message
                    def childType = ItemType.get(params.long('childTypeId'))
                    def sample = Sample.get(sampleId)
                    if (!sample) {
                        redirect(action: "showInstance", id: instanceId)
                    }
                    [sample: sample, instanceId: instanceId, childType: childType, item:item]
                }                
            }
        } else{
            def childType = ItemType.get(params.long('childTypeId'))
            def sample = Sample.get(sampleId)
            if (!sample) {
                redirect(action: "showInstance", id: instanceId)
            }
            [sample: sample, instanceId: instanceId, childType: childType]
        }
    }
    
    def removeChild(Long sampleId, Long instanceId) {
        protocolInstanceBagService.removeChild(sampleId)
        redirect(action: "showInstance", id: instanceId)
    }
}
