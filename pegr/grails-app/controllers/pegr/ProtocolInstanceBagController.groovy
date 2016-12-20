package pegr
import org.springframework.web.multipart.MultipartHttpServletRequest 

class ProtocolInstanceBagController {

    def springSecurityService
    def protocolInstanceBagService
    def protocolService
    def barcodeService
    def itemService
    
    def index() {
        redirect(action: "list", params: params )
    } 
    
    def list(int max, int offset, Long projectId, String status) {
        max = Math.min(max ?: 25, 100)

        def bags, totalCount
        if (projectId) {
            def projectBags = ProjectBags.createCriteria().list(max: max, offset: offset) {
                project {
                    eq("id", projectId)
                }
                bag {
                    order("startTime", "desc")
                }
            }
            totalCount = projectBags.totalCount
            bags = projectBags.collect { it.bag }
        } else if (status) {
            bags = ProtocolInstanceBag.where {status == status}.list(max: max, offset: offset, sort: "startTime", order: "desc")
            totalCount = ProtocolInstanceBag.where {status == status}.count()
        } else {
            bags = ProtocolInstanceBag.list(max: max, offset: offset)
            totalCount = ProtocolInstanceBag.count()
        }
        
        [bags: bags, totalCount: totalCount]
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
            def projects = params.list("projects")
            def prtclInstBag = (params.protocolInput == "defined") ? protocolInstanceBagService.savePrtclInstBagByGroup(protocolGroupId, bagName, params.startTime) : protocolInstanceBagService.savePrtclInstBagByProtocols(params.list('protocols'), bagName, params.startTime)
            protocolInstanceBagService.addBagToProjects(prtclInstBag, projects)
            redirect(action: "showBag", id: prtclInstBag.id)
        }catch( ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "create")
        }        
    }
    
    def edit(Long bagId) {
        def bag = ProtocolInstanceBag.get(bagId)
        [bag: bag]
    }
        
    def update(Long bagId, String name) {
        try {
            def projectIds = params.list("projects")
            protocolInstanceBagService.updateBag(bagId, name, projectIds)
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "showBag", id: bagId)
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
            tracedSamples = protocolInstanceBagService.getTracedSamples(id)
        }
        if (bag) {
            [bag:bag, count: count, protocolInstances: protocolInstances, notStarted: notStarted, completed: completed, toBeCompleted: toBeCompleted, tracedSamples: tracedSamples]
        }else {
            render status: 404
        }
    }
    
    def deleteBag(Long bagId) {
        try {
            protocolInstanceBagService.deleteBag(bagId)
            flash.message = "The protocol instance bag has been deleted!"
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "list")
    }
    
    def reopenBag(Long bagId) {
        try {
            protocolInstanceBagService.reopenBag(bagId)
            flash.message = "The protocol instance bag has been reopened!"
            redirect(action: "showBag", id: bagId)
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "list")
        }
    }
    
    def searchItemForBag(Long bagId){
        [bagId: bagId]       
    }
    
    def previewItemAndBag(Long typeId, String barcode, Long bagId) {
        def itemType = ItemType.get(typeId)
        def item = Item.findByBarcode(barcode)
        if (item) {        
            if (item.type == itemType) {
                def itemId = item.id
                def priorInstance = ProtocolInstanceItems.where {item.id == itemId}.get(sort:"id", order: 'desc', max: 1)
                render(view:"previewItemAndBag", model: [item: item, priorInstance: priorInstance?.protocolInstance, bagId: bagId])                
            } else {
                flash.message = "The item with barcode ${barcode} has type ${item.type}, which does not match the input type ${itemType}!"
                redirect(action: "searchItemForBag", params: [bagId: bagId])
            }
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
            itemService.updateCustomizedFields(item, params)
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
                                                 parents: results.parents,
                                                 children: results.children,
                                                 childType: protocol.endItemType,
                                                 file: file])
                } else {           
                    if (protocolInstance.status != ProtocolStatus.COMPLETED) {
                        toBeCompleted = protocolInstanceBagService.readyToBeCompleted(sharedItemAndPoolList, results, protocolInstance)
                    }                    
                    render(view: "editInstance", model: [protocolInstance: protocolInstance, 
                                                 sharedItemAndPoolList: sharedItemAndPoolList,
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
        if (!itemType) {
            flash.message = "Item type not found!"
            return
        }
        if (params.generate) {
            try {
                barcode = barcodeService.generateBarcode()
                render(view: "generateBarcode", model: [itemType: itemType, barcode: barcode, instanceId: instanceId])
            } catch (BarcodeException e) {
                flash.message = e.message
                redirect(action: "showInstance", id: instanceId)
            }
        } else {
            def item = Item.findByBarcode(barcode)
            if (item && item.type != itemType) {
                flash.message = "The item with barcode ${barcode} has type ${item.type}, which does not match the input type ${itemType}!"
                redirect(action: "showInstance", id: instanceId)
                return
            }
            if (itemType.category.superCategory == ItemTypeSuperCategory.SAMPLE_POOL) {
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
                        flash.message = "Imported pool must be pre-existing and created pool must be new!"
                        redirect(action: "showInstance", id: instanceId)
                    }
                } else {
                    flash.message = "Protocol instance not found!"
                    redirect(action: "list")
                }
            } else {
                if (item) {
                    render(view:"previewItemInInstance", model: [item: item, instanceId: instanceId])
                } else {
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
            itemService.updateCustomizedFields(item, params) 
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
            redirect(action:"list")
        } catch(ProtocolInstanceBagException e){
            flash.message = e.message
            redirect(action: "showBag", id: bagId)
        }

    }
    
    def addIndex(Long instanceId, String indexType) {
        def itemId = params.list('itemId')
        def indexIds = params.list('indexId')
        try {
            protocolInstanceBagService.addIndex(itemId, indexIds, indexType)
            flash.message = "Index saved!"            
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message  
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error saving the index!"
        }        
        redirect(action: "showInstance", id: instanceId)
    }
    
    def searchAntibody(Long itemId, Long instanceId) {
        def antibodyTypeId = ItemType.findByName('Antibody')
        [instanceId: instanceId, itemId: itemId, antibodyTypeId: antibodyTypeId]
    }
    
    def previewAntibody(Long itemId, Long instanceId, String barcode) {
        def item = Item.where{type.name == "Antibody" && barcode == barcode}.get(max: 1)
        if (item) {
            def antibody = Antibody.findByItem(item)
            if (antibody) {
                render(view: "previewAntibody", model: [antibody: antibody, instanceId: instanceId, itemId: itemId] )
            } 
        } else {
            flash.message = "Antibody not found!"
            redirect(action: "searchAntibody", params: [instanceId: instanceId, itemId: itemId])
        }
    }
    
    def addAntibodyToSample(Long itemId, Long instanceId, Long antibodyId) {
        try{
            protocolInstanceBagService.addAntibodyToSample(itemId, antibodyId)
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            flash.message = "Error adding the antibody to the sample!"
        }
        redirect(action: "showInstance", id: instanceId)
    }
    
    def removeAntibodyFromTracedSample(Long itemId, Long instanceId) {
        protocolInstanceBagService.removeAntibodyFromTracedSample(itemId)
        redirect(action: "showInstance", id: instanceId)
    }
    
    def addChild(Long parentItemId, Long instanceId) {
        if (request.method == "POST") {
            withForm {
                def item = new Item(params)
                try {
                    if (params.split) {
                        protocolInstanceBagService.splitChildren(item, parentItemId, instanceId)
                    } else {
                        protocolInstanceBagService.addChild(item, parentItemId, instanceId)
                    }
                    itemService.updateCustomizedFields(item, params)    
                } catch(ProtocolInstanceBagException e){
                    flash.message = e.message 
                }                
                redirect(action: "showInstance", id: instanceId)  
            }
        } else{
            def childType = ItemType.get(params.long('childTypeId'))
            def parentItem = Item.get(parentItemId)
            if (!parentItem) {
                redirect(action: "showInstance", id: instanceId)
            }
            [parentItem: parentItem, instanceId: instanceId, childType: childType, split: params.split]
        }
    }
    
    def removeChild(Long childItemId, Long instanceId) {
        try {
            protocolInstanceBagService.removeChild(childItemId, instanceId)
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
    
    def addAllChildren(Long instanceId) {
        try {
            protocolInstanceBagService.addAllChildren(instanceId)
        } catch (ProtocolInstanceBagException e) {
            flash.message = e.message
        }
        redirect(action: "showInstance", id: instanceId)
    }

    def showAllTracedSampleBarcodes(Long instanceId, String type, int row, int column) {
        def instance = ProtocolInstance.get(instanceId)
        if (!instance) {
            render(view: "/404")
            return
        }
        def results = protocolInstanceBagService.getParentsAndChildrenForInstance(instance, instance.protocol.startItemType, instance.protocol.endItemType)
        def items = []
        def priorCount = 5 * (row - 1) + column - 1
        for (int i = 0; i < priorCount; ++i) {
            items.push(null)
        }
        def tracedSamples = (type == "Parents") ? results.parents : results.children  
        tracedSamples.each { item ->
            items.push(item)
        }
        render(view:"/item/generateBarcodeList", model: [barcodeList: items*.barcode, nameList: items*.name*.take(20), date: new Date()])        
    }
    
    def search(String str) {
        def c = ProtocolInstanceBag.createCriteria()
        def listParams = [
                max: params.max ?: 25,
                sort: params.sort ?: "id",
                order: params.order ?: "desc",
                offset: params.offset
            ]
        def bags = c.list(listParams) {
            or {
                ilike "name", "%${str}%"
            }
        }
            
        render(view: "list", model: [bags: bags, totalCount: bags.totalCount, str: str])   
    }
    
    def uploadImage(Long instanceId, String type) {
        try {
            def instance = ProtocolInstance.get(instanceId)
            def fieldName = "image"
            protocolInstanceBagService.upload((MultipartHttpServletRequest)request, instance, type, fieldName)
            flash.message = "Image uploaded!"
        } catch(UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "showInstance", id: instanceId)
    }
    
    def removeInstanceImageAjax(Long instanceId, String filepath) {
        protocolInstanceBagService.removeInstanceImage(instanceId, filepath)
        render ""
        return
    }
}
