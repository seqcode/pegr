package pegr
import grails.converters.*

class CellSourceController {
    
    def springSecurityService
    def protocolInstanceBagService
    def cellSourceService
	def utilityService
    
        
    def create(Item item, CellSource cellSource) {
        [item: item, cellSource: cellSource]
    }
    
    def save() {
        withForm{        
            def item = new Item(params)
            def cellSource = new CellSource(params)
            def treatments = params.list("treatments")
            if (!(cellSource?.strain?.id)) {
                cellSource.strain = new Strain(params)
                cellSource.strain.name = params.strainName
                if (!(cellSource?.strain?.species?.id)) {
                    cellSource.strain.species = new Species(genusName:params.genusName, 
                                                            name: params.speciesName)
                }
            }
            try {
                cellSourceService.save(item, cellSource, treatments)
                flash.message = "New traced sample added!"
                redirect(action: "show", id: item.id)
            }catch(ItemException e) {
                request.message = e.message
                render(view: "createTracedSample", model: [item:item, cellSource: cellSource])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                request.message = "Error saving this item!"
                render(view: "createTracedSample", model: [item:item, cellSource: cellSource])
            }
        }
    }
    
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
        render utilityService.stringToSelect2Data(strains) as JSON
    }
    
    def fetchStrainNameAjax(String parentStrain, Long speciesId) {
        def strains = Strain.executeQuery("select distinct s.name from Strain s where s.parent.name = ? and s.species.id = ?", [parentStrain, speciesId])
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
        def growthMedias = GrowthMedia.where { (species == null) || (species == selectedSpecies) }.collect{it.name}
        render utilityService.stringToSelect2Data(growthMedias) as JSON
    }
    
    def fetchTreatmentsAjax() {
        def treatments = CellSourceTreatment.executeQuery("select t.name from CellSourceTreatment t")
        render utilityService.stringToSelect2Data(treatments) as JSON
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

class CellSourceCommand {
    String genus
    String speciesId
    String parentStrain
    String strain
    String genotype
    String mutation
    String tissue
    String age
    String sex
    String histology
    String growthMedia
    String treatments
    Long providerId
    Long providerLabId
    String bioSourceId
}