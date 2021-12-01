package pegr
import org.springframework.web.multipart.MultipartHttpServletRequest 
import java.text.SimpleDateFormat

class ProtocolInstanceBagController {

    def springSecurityService
    def protocolInstanceBagService
    def protocolService
    def barcodeService
    def itemService
    
    def index() {
        redirect(action: "list", params: params )
    } 
    
    def list(int max, int offset, Long projectId, String status, String nameSub) {
        max = Math.min(max ?: 25, 100)
        offset = offset ?: 0
        
        def c, bags, totalCount

        if (status == "null") {
            status = null
        }        
        
        if (projectId) {
            def projectBags = ProjectBags.createCriteria().list(max: max, offset: offset) {
                and {
                    project {
                        eq("id", projectId)
                    }
                    bag {
                        and {
                            if (nameSub) {
                                ilike "name", "%${nameSub}%"   
                            }
                            if (status) {
                                eq("status", status as ProtocolStatus)
                            }
                        }
                        order("id", "desc")
                    }
                }
            }
            bags = projectBags.collect { it.bag }
            totalCount = projectBags.totalCount
        } else {
            c = ProtocolInstanceBag.createCriteria()
            bags = c.list(max: max, offset: offset, sort: "id", order: "desc") {
                and {
                    if (nameSub) {
                        ilike "name", "%${nameSub}%"   
                    }
                    if (status) {
                        eq("status", status as ProtocolStatus)
                    }
                }
            }
            totalCount = bags.totalCount
        }
        
        [bags: bags, totalCount: totalCount, status: status, nameSub: nameSub, projectId: projectId]
    }
        
    def create() {
        def user = springSecurityService.currentUser
        def protocolGroups = ProtocolGroup.list()
        def sdf = new SimpleDateFormat("yyMMdd")
        def date = sdf.format(new Date())
        def name = "${date}_${user.username}_"
        [protocolGroups: protocolGroups, user: user, name: name]
    }
    
    def savePrtclInstBag(Long protocolGroupId, String bagName) {        
        try {
            def projects = params.list("projects")
            def prtclInstBag = (params.protocolInput == "defined") ? protocolInstanceBagService.savePrtclInstBagByGroup(protocolGroupId, bagName, params.getDate('startTime')) : protocolInstanceBagService.savePrtclInstBagByProtocols(params.list('protocolList'), bagName, params.getDate('startTime'))
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
    
    def previewItemAndBag(Long bagId) {
        def itemIds = params.list("items")
        def items = []
        itemIds.each { id ->
            if (id.isLong()) {
                def item = Item.get(id)
                if (item) {
                    items << item
                }
            }
        }
      
        def priorInstance
        if (items.size() == 1) {
            def itemId = items[0].id
            priorInstance = ProtocolInstanceItems.where {item.id == itemId}.get(sort:"id", order: 'desc', max: 1)
        }
        [items: items, priorInstance: priorInstance?.protocolInstance, bagId: bagId]              
    }
    
    def addItemsToBag(Long bagId) {
        try {
            def itemIds = params.list("itemIds")
            if (params.split) {
                protocolInstanceBagService.splitAndAddItemsToBag(itemIds, bagId)
            } else {
                protocolInstanceBagService.addItemsToBag(itemIds, bagId)
            }            
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
            if (!file?.exists()) {
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
                flash.message = "The item with barcode ${barcode} has type ${item.type.name}, which does not match the input type ${itemType.name}!"
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
        if (file?.exists()) {
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
            items << null
        }
        def tracedSamples = (type == "Parents") ? results.parents : results.children  
        tracedSamples.each { item ->
            items << item
        }
        render(view:"/item/generateBarcodeList", model: [barcodeList: items*.barcode, nameList: items*.name*.take(20), date: new Date()])        
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
    
    def printTracedSamples(Long bagId) {
        def items = protocolInstanceBagService.getTracedSamples(bagId)
        render(view: "/item/generateBarcodeList", model: [barcodeList: items*.barcode, nameList: items*.name*.take(20), date: new Date()])
    }
    
    def searchTracedSampleWorksheet(Long bagId) {
        def bag = ProtocolInstanceBag.get(bagId)
        [bag:bag]
    }
}
