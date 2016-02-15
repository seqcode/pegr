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
            def subBag = (item.bags.empty)? null : item.bags.last()
            render(view:"previewItemAndBag", model: [item: item, subBag: subBag, bagId: bagId])
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
            // get the existing items
            def protocolItems = ProtocolInstanceItems.where {protocolInstance.id == id}.collect {it.item}
            // shared item list
            def sharedItemList = []
            protocolInstance.protocol.sharedItemTypes.each{ t ->
                sharedItemList.add([type: t, items: []])
            }
            // insert items to the shared item list
            protocolItems.each{ item ->
                def itemsInType = sharedItemList.find{it.type == item.type}
                if (itemsInType) {
                    itemsInType.items.add(item)
                }else {
                    sharedItemList.add([type: item.type, items: [item]])
                }
            }        
            
            def completed = (protocolInstance.bag.status == ProtocolStatus.COMPLETED)
            def toBeCompleted = sharedItemList.every{ !it.items.empty } && (protocolInstance.status != ProtocolStatus.COMPLETED)
            [protocolInstance:protocolInstance, 
             sharedItemList: sharedItemList, 
             completed: completed, 
             toBeCompleted: toBeCompleted]
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
}
