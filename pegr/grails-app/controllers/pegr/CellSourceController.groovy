package pegr
import grails.converters.*

class CellSourceController {
    
    def springSecurityService
    def protocolInstanceBagService
    def cellSourceService
	def utilityService
    
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
        
    /* ----------------------------- Ajax ----------------------*/
    def fetchUserAjax() {
        def users = User.list().collect{[it.id, it.toString()]}
        render utilityService.arrayToSelect2Data(users) as JSON
    }
    
    def fetchGenusAjax() {
        def genusList = Species.executeQuery("select distinct s.genusName from Species s")
        render utilityService.stringToSelect2Data(genusList) as JSON
    }
    
    def fetchSpeciesAjax(String genus) {
        def species = Species.findAllByGenusName(genus)
        render utilityService.objectToSelect2Data(species) as JSON
    }
    
    def fetchGenomeAjax(Long speciesId) {
        def genomes = Genome.executeQuery("select g.name from Genome g where g.species.id = ?", [speciesId])
        render utilityService.stringToSelect2Data(genomes) as JSON
    }
    
    def fetchParentStrainAjax(Long speciesId) {
        def strains = Strain.executeQuery("select distinct s.parent.name from Strain s where s.species.id = ?", [speciesId])
        def nullStrain = ["Unknown"]
        render utilityService.stringToSelect2Data(nullStrain + strains) as JSON
    }
    
    def fetchStrainNameAjax(String parentStrain, Long speciesId) {
        def strains
        if (parentStrain == "Unknown") {
            strains = Strain.executeQuery("select distinct s.name from Strain s where s.parent is null and s.species.id = ?", [speciesId]) 
        } else {
            strains = Strain.executeQuery("select distinct s.name from Strain s where s.parent.name = ?", [parentStrain])
        }
        render utilityService.stringToSelect2Data(strains) as JSON
    }
    
    def fetchGenotypeAjax(String strainName) {
        def genotypes = Strain.executeQuery("select distinct s.genotype from Strain s where s.name = ?", [strainName])
        render utilityService.stringToSelect2Data(genotypes) as JSON
    }
    
    def fetchMutationAjax(String strainName, String genotype) {
        def mutations = Strain.executeQuery("select distinct s.geneticModification from Strain s where s.name = ? and s.genotype = ?", [strainName, genotype])
        render utilityService.stringToSelect2Data(mutations) as JSON
    }
    
    def fetchGrowthMediaAjax(Long speciesId) {
        def selectedSpecies = Species.get(speciesId)
        def growthMedias = GrowthMedia.where { (species == null) || (species == selectedSpecies) }
        render utilityService.objectToSelect2Data(growthMedias) as JSON
    }
    
    def fetchTreatmentsAjax() {
        def treatments = CellSourceTreatment.executeQuery("select t.name from CellSourceTreatment t")
        render utilityService.stringToSelect2Data(treatments) as JSON
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
}
