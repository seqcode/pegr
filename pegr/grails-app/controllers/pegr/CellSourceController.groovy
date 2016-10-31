package pegr
import grails.converters.*

class CellSourceController {
    
    def springSecurityService
    def protocolInstanceBagService
    def cellSourceService
	def utilityService
    def itemService
    
    final String CELL_STOCK = "Cell Stock"
    
    def list(Integer max) {
        def itemTypes = ItemType.list(sort: "name")
        def strains = Strain.executeQuery("select distinct name from Strain where name is not null order by name")
        params.max = Math.min(max ?: 15, 100)
        def strainName = params.strain
        def c = CellSource.createCriteria()
        def cellSources = c.list(params) {
            and {
                if (strainName) {
                    strain {
                        eq "name", strainName
                    }
                }
                eq "status", DictionaryStatus.Y
            }
        }
        def category = ItemTypeCategory.findByName(CELL_STOCK)

        [cellSources: cellSources, categoryId: category.id, itemTypes: itemTypes, strains: strains]
    }
    
    def show(Integer id) {
        def cellSource = CellSource.get(id)
        [cellSource: cellSource]
    }
    
    def save(Item item, CellSourceCommand cmd) {
        withForm {
            try {
                cellSourceService.save(item, cmd)
                itemService.updateCustomizedFields(item, params)
                flash.message = "New traced sample added!"
                redirect(controller: "item", action: "show", id: item.id)
            }catch(ItemException e) {
                request.message = e.message
                render(view: "createTracedSample", model: [item:item, cellSource: cmd])
            }catch(Exception e) {
                log.error "Error: ${e.message}", e
                request.message = "Error saving this item!"
                render(view: "createTracedSample", model: [item:item, cellSource: cmd])
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
            providerId: cellSource.providerUser?.id,
            providerLabId: cellSource.providerLab?.id,
            bioSourceId: cellSource.biologicalSourceId
        )
        [cellSource: cmd]
    }
    
    def update(CellSourceCommand cmd) {
        try {
            cellSourceService.update(cmd)
            flash.message = "Cell source information updated!"
            redirect(controller: "item", action:"show", id: cmd?.itemId)
        } catch (CellSourceException e) {
            flash.message = e.message
            redirect(action: "edit", params: [cellSourceId: cmd.cellSourceId])
        } catch (Exception e) {
            flash.message = "Error updating the cell source!"
            log.error "Error: ${e.message}", e
            redirect(action: "edit", params: [cellSourceId: cmd.cellSourceId])
        }

    }
    
    def batchCreate() {
        def category = ItemTypeCategory.findByName(CELL_STOCK)
        if (category) {
            [categoryId: category.id]
        } else {
            render(view: "/404")
        }
    }
    
    def batchSave(CellStockBatchCommand cmd) {
        def categoryId = ItemTypeCategory.findByName(CELL_STOCK)?.id
        try {
            cellSourceService.batchSave(cmd.items, cmd.cellSources)
        } catch(CellSourceException e) {
            flash.message = e.message
        }
        redirect(controller: "item", action: "list", params: [categoryId: categoryId])
    }
        
    /* ----------------------------- Ajax ----------------------*/    
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
    
    def editBarcode(Long cellSourceId) {
        def cellSource = CellSource.get(cellSourceId)
        if (cellSource) {        
            if (request.method == "POST") {
                withForm {
                    def item = new Item(params)
                    try {
                        cellSourceService.updateItem(cellSourceId, item)
                        itemService.updateCustomizedFields(cellSource.item, params)
                        flash.message = "Barcode edited!"
                        redirect(action: "show", id: cellSourceId)
                    }catch(CellSourceException e) {
                        request.message = e.message
                        render(view: "editBarcode", model: [item:item, cellSourceId: cellSourceId])
                    }                    
                }
            } else {
                def types = ItemType.where {category.name == CELL_STOCK}.list()
                def item = cellSource.item
                if (!item) {
                    item = new Item(name: cellSource.strain?.name)
                }                
                [item:item, types: types, cellSourceId: cellSourceId]
            }
        } else {
            flash.message = "Cell stock not found!"
            redirect(action: 'list')
        }
    }
    
    def delete(Long cellSourceId) {
        try {
            cellSourceService.delete(cellSourceId)
            flash.message = "Cell stock deleted!"
            redirect(action: 'list')
        } catch(CellSourceException e) {
            flash.message = e.message
            redirect(action: "show", id: cellSourceId)
        }
    }
}

@grails.validation.Validateable
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
    Long providerId
    Long providerLabId
    String bioSourceId
}

@grails.validation.Validateable
class CellStockBatchCommand {
    List<Item> items = [].withLazyDefault {new Item()}
    List<CellSourceCommand> cellSources = [].withLazyDefault {new CellSourceCommand()}
}