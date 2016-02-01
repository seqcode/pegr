package pegr
import grails.transaction.Transactional

class CellSourceController {
    
    def springSecurityService
    def protocolInstanceBagService
    def cellSourceService
    
    def updateTreatments(){
        def cellSource = CellSource.get(params.long('cellSourceId'))
        if (!cellSource) {
            redirect(controller: "item")
        }
        if(request.method == "POST") {
            withForm {
                try {
                    cellSourceService.updateTreatments(cellSource, params.list('treatments'))
                }catch(Exception e){
                    flash.message = "Error saving the treatments!"
                    log.error "Error: ${e.message}", e
                }
                redirect(controller: "item", action: 'show', id: params.long('itemId'))
            }
        } else {
            [cellSourceId: params.long('cellSourceId'), itemId: params.long('itemId'), treatments: cellSource.treatments]
        }
    }
    
   def showCellSourceForSample(Integer sampleId) {
        def sample = Sample.get(sampleId)
		
		if (sample.cellSource) {
			def item = Item.where {
				(type.name=="Cell Source") && (referenceId == sample.cellSource.id)
			}.get(max: 1)
			[cellSourceInstance: sample.cellSource, itemInstance: item, sampleId: sampleId]
		} else {
			redirect(action:"createCellSourceForSample", params: [sampleId: sampleId])
		}
    }
	
    @Transactional
	def createCellSourceForSample() {
		if (params.sampleId) {
			if(request.method != "POST") {
				render(view: "createCellSourceForSample", model:[sampleId: params.sampleId]) 
			}else{
				withForm {
					def sample = Sample.get(params.sampleId)
					if(sample) {
						def cellSource = new CellSource(params)
						def itemType = ItemType.findByName("Cell Source")
						def item = new Item(type: itemType)
						item.properties['barcode', 'location', 'note'] = params
						if (cellSource.validate() && cellSource.save(flush:true)) {
							sample.cellSource = cellSource
							sample.save()
							
							item.referenceId = cellSource.id
							if (item.validate()){
								item.save(flush:true)
								redirect(action: "showCellSourceForSample", params: [sampleId: params.sampleId])
							}
						}
					    flash.message = "Invalid inputs"
                        render( model: [cellSourceInstance: cellSource, itemInstance: item,
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
	def editCellSourceForSample() {
		
	}
	
}
