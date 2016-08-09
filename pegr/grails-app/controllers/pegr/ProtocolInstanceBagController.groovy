package pegr
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ProtocolInstanceBagController {

    def springSecurityService
    def protocolInstanceBagService
    def protocolService
    def barcodeService
    
    def index() {
        redirect(action: "processingBags", params: params )
    } 
    
    def processingBags(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "startTime"
            params.order = "desc"
        }
        def bags = ProtocolInstanceBag.where { status != ProtocolStatus.COMPLETED }.list(params)
        [bags: bags]
    }
    
    def completedBags(Integer max){
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "startTime"
            params.order = "desc"
        }
        def bags = ProtocolInstanceBag.where { status == ProtocolStatus.COMPLETED }.list(params)
        [bags: bags]
    }
        
    def create() {
        def user = springSecurityService.currentUser
        def protocolGroups = ProtocolGroup.list()
        def date = new Date().format("yyMMdd")
        def name = "${date}_${user.username}_"
        [protocolGroups: protocolGroups, user: user, name: name]
    }
    
    def savePrtclInstBag(Long protocolGroupId, String bagName) {        
        try {
            def prtclInstBag = (params.protocolInput == "defined") ? protocolInstanceBagService.savePrtclInstBagByGroup(protocolGroupId, bagName, params.startTime) : protocolInstanceBagService.savePrtclInstBagByProtocols(params.list('protocols'), bagName, params.startTime)
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
        def toBeCompleted, notStarted, tracedSamples
        if (protocolInstances.size() > 0) {
            toBeCompleted = (bag.status != ProtocolStatus.COMPLETED && protocolInstances.last().status == ProtocolStatus.COMPLETED)
            notStarted = (protocolInstances[0].status == ProtocolStatus.INACTIVE)
            def instance = protocolInstances.find { it.status == ProtocolStatus.PROCESSING }
            if (!instance) {
                instance = (protocolInstances[0].status == ProtocolStatus.INACTIVE) ? protocolInstances.first() : protocolInstances.last()
            } 
            tracedSamples = ProtocolInstanceItems.findAllByProtocolInstance(instance).collect { it.item }
        }
        if (bag) {
            [bag:bag, count: count, protocolInstances: protocolInstances, notStarted: notStarted, completed: completed, toBeCompleted: toBeCompleted, tracedSamples: tracedSamples]
        }else {
            render status: 404
        }
    }
    
    def updateBagAjax(Long bagId, String name) {
        protocolInstanceBagService.updateBag(bagId, name)
        render name
    }
    
    def deleteBag(Long bagId) {
        try {
            protocolInstanceBagService.deleteBag(bagId)
            flash.message = "The protocol instance bag has been deleted!"
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "processingBags")
    }
    
    def reopenBag(Long bagId) {
        try {
            protocolInstanceBagService.reopenBag(bagId)
            flash.message = "The protocol instance bag has been reopened!"
            redirect(action: "showBag", id: bagId)
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "processingBags")
        }
    }
    
    def searchItemForBag(Long bagId){
        [bagId: bagId]       
    }
    
    def previewItemAndBag(Long typeId, String barcode, Long bagId) {
        def itemType = ItemType.get(typeId)
        def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
        if (item) {        
            def itemId = item.id
            def priorInstance = ProtocolInstanceItems.where {item.id == itemId}.get(sort:"id", order: 'desc', max: 1)
            render(view:"previewItemAndBag", model: [item: item, priorInstance: priorInstance, bagId: bagId])
        } else {
            flash.message = "No item found!"
            redirect(action: "searchItemForBag", params: [bagId: bagId])
        }
    }
    
    def addItemToBag(Long itemId, Long bagId) {
        try {
            protocolInstanceBagService.addItemToBag(itemId, bagId)
            redirect(action: "showBag", id: bagId)
        }catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "searchItemForBag", params: [bagId: bagId])
        }
    }
    
    def splitAndAddItemToBag(Long itemId, Long bagId, Item item) {
        try {
            protocolInstanceBagService.splitAndAddItemToBag(itemId, bagId, item)
            redirect(action: "showBag", id: bagId)
        }catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "searchItemForBag", params: [bagId: bagId])
        }
    }
    
    def addSubBagToBag(Long instanceId, Long bagId) {
        try {
            protocolInstanceBagService.addSubBagToBag(instanceId, bagId)
            flash.message = "success adding the traced samples!"
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "showBag", id: bagId)
    }
    
    def removeSampleFromBag(Long itemId, Long bagId) {
        try {
            protocolInstanceBagService.removeSampleFromBag(itemId, bagId)
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
            def file = protocolService.getProtocolFile(protocol.id)
            if (!file.exists()) {
                file = null
            }
            // get shared item list
            def sharedItemAndPoolList = protocolInstanceBagService.getSharedItemAndPoolList(id, protocol)
            
            // prepare the individual sample table template
            // set addChild to be true if end item type is different from the start item type
            def addChild = (protocol.startItemType 
                        && protocol.endItemType 
                        && protocol.startItemType != protocol.endItemType)
            def completed = (protocolInstance.bag.status == ProtocolStatus.COMPLETED)
            try{
                def toBeCompleted = false
                def results = protocolInstanceBagService.getParentsAndChildrenForInstance(protocolInstance, protocol.startItemType, protocol.endItemType)
                if (completed) {
                    render(view: "showInstance", model: [protocolInstance: protocolInstance, 
                                                 sharedItemAndPoolList: sharedItemAndPoolList,
                                                 samples: results.samples,
                                                 parents: results.parents,
                                                 children: results.children,
                                                 childType: protocol.endItemType,
                                                 file: file])
                } else {              
                    toBeCompleted = protocolInstanceBagService.readyToBeCompleted(sharedItemAndPoolList, results, protocol)
                    render(view: "editInstance", model: [protocolInstance: protocolInstance, 
                                                 sharedItemAndPoolList: sharedItemAndPoolList,
                                                 samples: results.samples,
                                                 parents: results.parents,
                                                 children: results.children,
                                                 childType: protocol.endItemType,
                                                 toBeCompleted: toBeCompleted,
                                                 file: file])
                }
            } catch(ProtocolInstanceBagException e) {
                flash.message = e.message
                redirect(action: "showBag", id: protocolInstance.bag.id)
            }
        }else {
            render status: 404
        }
    }
    
    def searchItemForInstance(Long instanceId){
        [instanceId: instanceId]       
    }
    
    def searchItemForTypeInstance(Long instanceId, Long typeId){
        def itemType = ItemType.get(typeId)
        [instanceId: instanceId, itemType: itemType]       
    }
    
    def previewItemInInstance(Long typeId, String barcode, Long instanceId) {
        def itemType = ItemType.get(typeId)
        if (params.generate) {
            try {
                barcode = barcodeService.generateBarcode()
                render(view: "generateBarcode", model: [itemType: itemType, barcode: barcode, instanceId: instanceId])
            } catch (BarcodeException e) {
                flash.message = e.message
                redirect(action: "showInstance", id: instanceId)
            }
        } else {
            def item = Item.where{type.id == typeId && barcode == barcode}.get(max:1)
            if (itemType && itemType.category == ItemTypeCategory.SAMPLE_POOL) {
                def instance = ProtocolInstance.get(instanceId)
                if (instance) {
                    if (instance.protocol.startPoolType == itemType && item) {
                        // start pool must be pre-existing    
                        render(view: "previewPoolInInstance", model: [instanceId: instanceId, item:item])
                    } else if (instance.protocol.endPoolType == itemType && !item) {
                        // end pool must be new
                        item = new Item(type: itemType, barcode: barcode)
                        render(view: "createItemInInstance", model: [instanceId: instanceId, item:item])    
                    } else {
                        flash.message = "Start pool must be pre-existing and end pool must be new!"
                        redirect(action: "showInstance", id: instanceId)
                    }
                } else {
                    flash.message = "Protocol instance not found!"
                    redirect(action: "processingBags")
                }
            } else {
                if (item) {
                    render(view:"previewItemInInstance", model: [item: item, instanceId: instanceId])
                }else {
                    item = new Item(type: itemType, barcode: barcode)
                    render(view: "createItemInInstance", model: [instanceId: instanceId, item:item])
                }
            }
        }
    }
    
    def addPoolToInstance(Long itemId, Long instanceId) {
        try {
            protocolInstanceBagService.addPoolToInstance(itemId, instanceId)
            flash.message = "Pool is added successfully!"
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
        }        
        redirect(action: "showInstance", id: instanceId)
    }
    
    def addItemToInstance(Long itemId, Long instanceId) {
        try {
            protocolInstanceBagService.addItemToInstance(itemId, instanceId)
            redirect(action: "showInstance", id: instanceId)
        }catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "searchItemForInstance", params:[instanceId: instanceId])
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
            redirect(action: "searchItemForInstance", params:[instanceId: instanceId])
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
            redirect(action:"processingBags")
        } catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "showBag", id: bagId)
        }

    }
    
    def addIndex(Long instanceId, String indexType) {
        def sampleId = params.list('sampleId')
        def indexIds = params.list('indexId')
        try {
            protocolInstanceBagService.addIndex(sampleId, indexIds, indexType)
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
                    if (params.split) {
                        protocolInstanceBagService.splitChildren(item, sampleId, instanceId)
                    } else {
                        protocolInstanceBagService.addChild(item, sampleId, instanceId)
                    }
                }catch(ProtocolInstanceBagException e){
                    flash.message = e.message 
                }                
                redirect(action: "showInstance", id: instanceId)  
            }
        } else{
            def childType = ItemType.get(params.long('childTypeId'))
            def sample = Sample.get(sampleId)
            if (!sample) {
                redirect(action: "showInstance", id: instanceId)
            }
            [sample: sample, instanceId: instanceId, childType: childType, split: params.split]
        }
    }
    
    def removeChild(Long sampleId, Long instanceId) {
        try {
            protocolInstanceBagService.removeChild(sampleId, instanceId)
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "showInstance", id: instanceId)
    }
    
    def renderFile(Long protocolId) {
        def file = protocolService.getProtocolFile(protocolId)
        if (file.exists()) {
            response.setHeader "Content-disposition", "inline; filename=${file.getName()}"
            response.contentType = 'application/pdf'
            response.outputStream << file
            response.outputStream.flush()
            render(contentType: "application/pdf", contentDisposition: "inline", file: file, fileName: file.getName())
        } else {
            render(view: "/404")
        }
    }

}
