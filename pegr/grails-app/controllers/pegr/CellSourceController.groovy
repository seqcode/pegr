package pegr
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CellSourceController {

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
						item.properties['barcode', 'location'] = params
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
                        render( model: [cellSourceInstance: cellSource,												                                               itemInstance: item,
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
	
}
