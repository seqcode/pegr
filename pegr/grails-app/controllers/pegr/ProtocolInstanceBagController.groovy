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
        def subBags = ProtocolInstanceBag.where{superBag.id == id}        
        if (bag) {
            [bag:bag, count: count, protocolInstances: protocolInstances, subBags: subBags]
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
            subBag = item.bags.last()
            render(view:"previewItemAndBag", model: [item: item, subBag: subBag, bagId: bagId])
        }else {
            item = new Item(type: itemType, barcode: barcode)
            render(view: "createItemInBag", model: [bagId: bagId, item:item])
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
    
    def addSubBagToBag(Long subBagId, Long bagId) {
        try {
            protocolInstanceBagService.addSubBagToBag(subBagId, bagId)
            redirect(action: "showBag", id: bagId)
        } catch(ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "searchItemForBag", params: [bagId: bagId])
        }
    }
    
    def saveItemInBag() {
        
        def item = new Item(params)
        def bagId = Long.parseLong(params.bagId)
        def parentTypeId = Long.parseLong(params.parentTypeId)
        try {
            protocolInstanceBagService.saveItemInBag(item, parentTypeId, parentBarcode, bagId)
            redirect(action: "showBag", id: bagId)
        }catch(ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "searchItemForBag", params: [bagId: bagId])
        }
    }
    
    def showInstance(Long prtclInstanceId) {
        def protocolInstance = ProtocolInstance.get(prtclInstanceId)
        if (protocolInstance) {
            [protocolInstance:protocolInstance]
        }else {
            render status: 404
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
    
    def showBaggedItemsAjax(Long id) {
        def bag = ProtocolInstanceBag.get(id)
        def subBags = ProtocolInstanceBag.where{superBag.id == id} 
        render template: 'baggedItems', model:[ items:bag.tracedItems,subBags:subBags]
    }
    
}
