package pegr

class CellSourceController {
    
    def springSecurityService
    def protocolInstanceBagService
    def cellSourceService
	
    def edit(CellSource cellSource) {
        [cellSource: cellSource, itemId: cellSource?.item?.id]
    }
    
    def update(Long id) {
        def cellSource = CellSource.get(id)
        if (cellSource) {
            cellSource.properties = params
            def treatments = params.list("treatments")
            try {
                cellSourceService.save(cellSource, treatments)
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
        
    def fetchStrainsForSpeciesAjax(Long id) {
        def selectedSpecies = Species.get(id)
        def strains = Strain.where {species == selectedSpecies}.list()
        render g.select(id: 'strain', name: 'strain.id', from: strains, optionKey: 'id', noSelection:[null:'Other'], onchange: "strainChanged(this.value);")
    }
    
    def showStrainDetailsAjax(Long id) {
        if (id) {
            def strain = Strain.get(id)
            render(template:"strainDetails", model: ['strain': strain])
        } else {
            render(template:"strainForm")
        }
    }
    
    def fetchGrowthMediasForSpeciesAjax(Long id) {
        def selectedSpecies = Species.get(id)
        def growthMedias = GrowthMedia.where{species == null || species == selectedSpecies}.list()
        render g.select(id: 'growthMedia', name:'growthMedia.id', from: growthMedias, optionKey: 'id', noSelection:[null:''])
    }
    
    def addTreatment() {
        def cellSourceId = params.long('cellSourceId')
        def treatment = new CellSourceTreatment(params)
        def cellSource = CellSource.get(cellSourceId)
        if (!cellSource) {
            render status:404
        } else {
            cellSourceService.addTreatment(treatment, cellSource)
            redirect(action:"edit", id:cellSourceId)
        }
    }
}
