package pegr
import grails.converters.*

class CellSourceController {
    
    def springSecurityService
    def protocolInstanceBagService
    def cellSourceService
	
    def edit(Long cellSourceId) {
        def cellSource = CellSource.get(cellSourceId)
        if (cellSource) {
            cellSource.properties = params
        }
        [cellSource: cellSource, itemId: cellSource?.item?.id]
    }
    
    def update(Long cellSourceId) {
        def cellSource = CellSource.get(cellSourceId)
        if (cellSource) {
            cellSource.properties = params
            def treatments = params.list("treatments")
            try {
                cellSourceService.save(cellSource, treatments)
                flash.message = "Cell source information updated!"
                redirect(controller: "item", action:"show", id: cellSource?.item?.id)
            } catch (ItemException e) {
                flash.message = e.message
                redirect(action: "edit", cellSourceId: cellSourceId)
            }
        } else {
            flash.message = "Cell source not found!"
            redirect(controller: "item", action: "list")
        }
    }
        
    def fetchGenusAjax() {
        def genusList = Species.executeQuery("select distinct s.genusName from Species s")
        render stringToSelect2Data(genusList) as JSON
    }
    
    def fetchSpeciesAjax(String genus) {
        def species = Species.findAllByGenusName(genus)
        render objectToSelect2Data(species) as JSON
    }
    
    def fetchParentStrainAjax(Long speciesId) {
        def strains = Strain.executeQuery("select distinct s.name from Strain s where s.species.id = ?", [speciesId])
        render stringToSelect2Data(strains) as JSON
    }
    
    def fetchStrainNameAjax(String parentStrain) {
        def strains = Strain.executeQuery("select distinct s.name from Strain s where s.parent.name = ?", [parentStrain])
        render stringToSelect2Data(strains) as JSON
    }
    
    def fetchGenotypeAjax(String strainName) {
        def genotypes = Strain.executeQuery("select distinct s.genotype from Strain s where s.name = ?", [strainName])
        render stringToSelect2Data(genotypes) as JSON
    }
    
    def fetchMutationaAjax(String strainName) {
        def mutations = Strain.executeQuery("select distinct s.geneticModification from Strain s where s.name = ?", [strainName])
        render stringToSelect2Data(mutations) as JSON
    }
    
    def fetchGrowthMediaAjax(Long speciesId) {
        def growthMedias = GrwothMedia.where {species.id == speciesId}
        render objectToSelect2Data(growthMedias)
    }
    
    /* ---------------------------- Obsolete -------------------------- */
    def fetchStrainsForSpeciesAjax(Long id) {
        def selectedSpecies = Species.get(id)
        def strains = Strain.where {species == selectedSpecies}.list(sort:'name')
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
        def growthMedias = GrowthMedia.where{species == null || species == selectedSpecies}.list(sort:'name')
        render g.select(id: 'growthMedia', name:'growthMedia.id', from: growthMedias, optionKey: 'id', noSelection:[null:''])
    }
    
    def addTreatment(Long cellSourceId) {
        // save the new treatment
        def treatment = new CellSourceTreatment(params)
        cellSourceService.addTreatment(treatment)
        def cellSource = CellSource.get(cellSourceId)
        def treatments = cellSource ? cellSource.treatments : []
        // add the new treatment to the cellSource for preview (not saved yet)
        treatments.push(treatment)
        render g.select(multiple:"multiple", name:"treatments", from:CellSourceTreatment.list(sort:'name'), optionKey:"id", value: treatments*.id, class:"select2")
    }
    
    def stringToSelect2Data(def strings) {
        return strings.collect {s -> [id: s, text: s]}
    }
    
    def objectToSelect2Data(def objects) {
        return objects.collect {o -> [id: o.id, text: o.name]}
    }
}
