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
    
    def save(Item item, CellSourceCommand cmd) {
        withForm {
            try {
                cellSourceService.save(item, cmd)
                flash.message = "New traced sample added!"
                redirect(controller: "item", action: "show", id: item.id)
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
        if (!cellSource) {
            render(view: "/404")
            return
        }
        def cmd = new CellSourceCommand(
            cellSourceId: cellSource.id,
            itemId: cellSource.item?.id,
            genus: cellSource.strain?.species?.genusName,
            speciesId: cellSource.strain?.species?.id,
            speciesName: cellSource.strain?.species?.name,
            parentStrain: cellSource.strain?.parent?.name,
            strain: cellSource.strain?.name,
            genotype: cellSource.strain?.genotype,
            mutation: cellSource.strain?.geneticModification,
            tissue: cellSource.tissue?.name,
            age: cellSource.age,
            sex: cellSource.sex?.name,
            histology: cellSource.histology?.name,
            growthMedia: cellSource.growthMedia?.name,
            providerId: cellSource.providerUser?.id,
            providerLabId: cellSource.providerLab?.id,
            bioSourceId: cellSource.biologicalSourceId
        )
        [cellSource: cmd, treatments: cellSource.treatments]
    }
    
    def update(CellSourceCommand cmd) {
        try {
            cellSourceService.update(cmd)
            flash.message = "Cell source information updated!"
            redirect(controller: "item", action:"show", id: cmd?.itemId)
        } catch (ItemException e) {
            flash.message = e.message
            redirect(action: "edit", params: [cellSourceId: cmd.cellSourceId])
        } catch (Exception e) {
            flash.message = "Error updating the antibody!"
            log.error "Error: ${e.message}", e
            redirect(action: "edit", params: [cellSourceId: cmd.cellSourceId])
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

}

class CellSourceCommand {
    Long cellSourceId
    Long itemId
    String genus
    String speciesId
    String speciesName
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