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
	
    def edit(CellSource cellSource) {
        [object: cellSource, itemId: cellSource?.item?.id]
    }
    
    def update(Long id) {
        def cellSource = CellSource.get(id)
        if (cellSource) {
            cellSource.properties = params
            try {
                cellSourceService.save(cellSource)
                flash.message = "Cell source information updated!"
                redirect(controller: "item", action:"show", id: cellSource?.item?.id)
            } catch (CellSourceException e) {
                flash.message = e.message
                redirect(action: "edit", id: id)
            }
        } else {
            flash.message = "Cell source not found!"
            redirect(controller: "item", action: "list")
        }
    }
        
    def strainChangedAjax(Long strainId) {
        def strain = Strain.get(strainId)
        def growthMedias = GrowthMedia.where{
            if(strain?.genotype?.species) {
                (species == null) || (species == strain?.genotype?.species)
            }
        }.list()
        render g.select(id: 'growthMedia', name:'growthMedia.id', from: growthMedias, optionKey: 'id', noSelection:[null:''])
    }
}
