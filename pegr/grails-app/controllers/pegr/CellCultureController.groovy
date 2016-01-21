package pegr
import grails.transaction.Transactional

class CellCultureController {
    
    def springSecurityService
    def protocolInstanceBagService
    
    def index() {
        redirect(action: "processingBags")
    }
    
    def processingBags() {
        def bags = protocolInstanceBagService.fetchIncomplete(PrtclInstBagType.CELL_CULTURE)
        [bags: bags]
    }
    
    def completedBags(){
        def bags = protocolInstanceBagService.fetchCompleted(PrtclInstBagType.CELL_CULTURE)
        [bags: bags]
    }
    
        
    def showBag(Long id) {
        def bag = ProtocolInstanceBag.get(id)
        def count = bag.protocolInstances.count{it.status == ProtocolStatus.COMPLETED}
        def cellCultures = CellCulture.where{prtclInstBag == bag}
        if (bag) {
            [bag:bag, count: count, cellCultures: cellCultures]
        }else {
            render status: 404
        }
    }
    
    
    def create() {
        def admin = User.get(1)
        def protocolGroups = ProtocolGroup.where { (user == admin) || (user == null) || (user == springSecurityService.currentUser)}
        [protocolGroups: protocolGroups]
    }
    
    def savePrtclInstBag(Long id) {        
        try {
            def prtclInstBag = protocolInstanceBagService.savePrtclInstBag(id, PrtclInstBagType.CELL_CULTURE)
            redirect(action: "addCellCultureToBag", params: [prtclInstBagId: prtclInstBag.id])
        }catch( ProtocolInstanceBagException e) {
            flash.message = e.message
            redirect(action: "create")
        }        
    }
    
    def addCellCultureToBag(Long prtclInstBagId){
        def bag = ProtocolInstanceBag.get(prtclInstBagId)
        if (bag) {
            def cellCultures = CellCulture.where {prtclInstBag == bag}
            [cellCultures: cellCultures, bagId: bag.id]
        } else {
            render status: 404
        }        
    }

    
   def showCellCultureForSample(Integer sampleId) {
        def sample = Sample.get(sampleId)
		
		if (sample.cellCulture) {
			def item = Item.where {
				(type.name=="Cell Culture") && (referenceId == sample.cellCulture.id)
			}.get(max: 1)
			[cellCultureInstance: sample.cellCulture, itemInstance: item, sampleId: sampleId]
		} else {
			redirect(action:"createCellCultureForSample", params: [sampleId: sampleId])
		}
    }
	
    @Transactional
	def createCellCultureForSample() {
		if (params.sampleId) {
			if(request.method != "POST") {
				render(view: "createCellCultureForSample", model:[sampleId: params.sampleId]) 
			}else{
				withForm {
					def sample = Sample.get(params.sampleId)
					if(sample) {
						def cellCulture = new CellCulture(params)
						def itemType = ItemType.findByName("Cell Culture")
						def item = new Item(type: itemType)
						item.properties['barcode', 'location', 'note'] = params
						if (cellCulture.validate() && cellCulture.save(flush:true)) {
							sample.cellCulture = cellCulture
							sample.save()
							
							item.referenceId = cellCulture.id
							if (item.validate()){
								item.save(flush:true)
								redirect(action: "showCellCultureForSample", params: [sampleId: params.sampleId])
							}
						}
					    flash.message = "Invalid inputs"
                        render( model: [cellCultureInstance: cellCulture, itemInstance: item,
                                    sampleId: sample.id])
						
                    } else {
                        render status: 500
                    }                    
				}
			}
		}else {
			render status: 500
		}
	}
	
	@Transactional 
	def editCellCultureForSample() {
		
	}
	
}
