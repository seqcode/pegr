package pegr

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
			[cellSourceInstance: sample.cellSource, itemInstance: sample.cellSource.item, sampleId: sampleId]
		} else {
			redirect(action:"createCellSourceForSample", params: [sampleId: sampleId])
		}
    }
	
    
}
