package pegr
import grails.converters.*
import org.springframework.web.multipart.MultipartHttpServletRequest 

class CellSourceController {
    
    def springSecurityService
    def protocolInstanceBagService
    def cellSourceService
	def utilityService
    def itemService
    def barcodeService
    
    final String CELL_STOCK = "Cell Stock"
    
    def list(Integer max) {
        params.max = Math.min(max ?: 50, 100)
        def itemTypes = itemService.getCategorizedItemTypes()
        
        def strains = Strain.executeQuery("select distinct name from Strain where name is not null order by name")
        def strainName = params.strain
        if (strainName == "null") {
            strainName = null
        }
        
        def speciesId = null
        if (params.speciesId && params.speciesId.isInteger()) {
            speciesId = params.speciesId as Long
        }
        
        def c = CellSource.createCriteria()
        def cellSources = c.list(params) {
            and {
                if (strainName) {
                    strain {
                        eq "name", strainName
                    }
                }
                if (speciesId) {
                    strain {
                        species {
                            eq "id", speciesId
                        }
                    }
                }
                eq "status", DictionaryStatus.Y
                if (params.active == "active") {
                    item {
                        eq "active", true
                    }
                } else if (params.active == "inactive") {
                    item {
                        eq "active", false
                    }
                } else if (params.active == 'noBarcode') {
                    isNull "item"
                }
            }
        }
        def category = ItemTypeCategory.findByName(CELL_STOCK)
        def orderLink = utilityService.getInventoryExternalLink()

        [cellSources: cellSources, categoryId: category.id, itemTypes: itemTypes, strains: strains, orderLink: orderLink, strainName: strainName, speciesId: speciesId, active: params.active]
    }
    
    def show(Integer id) {
        def cellSource = CellSource.get(id)
        if (cellSource) {
            def images
            if (cellSource.item) {
                def folder = itemService.getImageFolder(cellSource.item.id)
                images = folder.listFiles()
            }            
            [cellSource: cellSource, images: images]
        } else {
            render status: 404
        }
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
        try {
            def batch = cellSourceService.batchSave(cmd.cellSources)
            redirect(action: "showBatch", id: batch.id)
        } catch(CellSourceException e) {
            flash.message = e.message
            redirect(action: "list")
        }        
    }
        
    /* ----------------------------- Ajax ----------------------*/   
    // this is for the batchEdit page
    def fetchStrainAjax() {
        def result = [
            names: utilityService.stringToSelect2Data(Strain.list(sort:"name").collect{it.name}),
            genotypes: utilityService.stringToSelect2Data(Strain.list(sort:"genotype").collect{it.genotype}.unique()),
            geneticModifications: utilityService.stringToSelect2Data(Strain.list(sort:"geneticModification").collect{it.geneticModification}.unique())
        ]
        render result as JSON
    }
    
    def fetchGenusAjax() {
        def genusList = Species.executeQuery("select distinct s.genusName from Species s")
        render utilityService.stringToSelect2Data(genusList) as JSON
    }
    
    def fetchSpeciesAjax(String genus) {
        def species = Species.findAllByGenusName(genus)
        render utilityService.objectToSelect2Data(species) as JSON
    }
    
    def fetchGenomeAjax() {
        def genomes = Genome.executeQuery("select g.name from Genome g")
        render utilityService.stringToSelect2Data(genomes) as JSON
    }
    
    def fetchParentStrainAjax(Long speciesId) {
        def strains = Strain.executeQuery("select distinct s.parent.name from Strain s where s.species.id=:id", [id: speciesId])
        render utilityService.stringToSelect2Data(strains) as JSON
    }
    
    def fetchStrainNameAjax(String parentStrain, Long speciesId) {
        def strains = Strain.executeQuery("select distinct s.name from Strain s where s.parent.name=:parent and s.species.id=:id", [parent: parentStrain, id: speciesId])
        render utilityService.stringToSelect2Data(strains) as JSON
    }
    
    def fetchGenotypeAjax(String strainName) {
        def genotypes = Strain.executeQuery("select distinct s.genotype from Strain s where s.name=:strain", [strain: strainName])
        render utilityService.stringToSelect2Data(genotypes) as JSON
    }
    
    def fetchMutationAjax(String strainName, String genotype) {
        def mutations = Strain.executeQuery("select distinct s.geneticModification from Strain s where s.name=:strain and s.genotype=:genotype", [strain: strainName, genotype: genotype])
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
    
    def importCellStock() {
        def filesroot = utilityService.getFilesRoot()
        try {
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;  
            def mpf = mpr.getFile("file");
            String filename = mpf.getOriginalFilename();
            if(!mpf?.empty && filename[-4..-1] == ".csv") {  
                File folder = new File(filesroot, 'temp'); 
                if (!folder.exists()) { 
                    folder.mkdirs(); 
                } 
                File fileDest = new File(folder, filename)
                mpf.transferTo(fileDest)
                
                def batch = cellSourceService.importCellStock(fileDest.getPath(), 
                                              params.int("startLine"), 
                                              params.int("endLine"))
                flash.message = "New cell stockes have been uploaded!"
                redirect(action: "showBatch", id: batch.id)
            } else {
                flash.message = "Only csv file can be accepted!"
                redirect(action: "list")
            }
        } catch (CellSourceException e) {
            flash.message = e.message
            redirect(action: "list")
        }        
    }
    
    def listBatches() {
        def batches = CellSourceBatch.list()
        [batches: batches]
    }
    
    def showBatch(Long id) {
        def batch = CellSourceBatch.get(id)
        [batch: batch]
    }
    
    def batchGenerateBarcodeAjax(int id) {
        def batch = CellSourceBatch.get(id)
        render barcodeService.generateBarcodeList(batch.cellSources.size()) as JSON
        return
    }
    
    def saveItems(ItemBatchCommand cmd) {
        try {
            cellSourceService.saveItems(cmd)
        } catch (Exception e) {
            flash.message = "Error saving the barcodes!"
        }
        redirect(action: "showBatch", id: cmd.batchId)
    }
    
    def printBatchBarcode(Long id, int row, int col) {
        def batch = CellSourceBatch.get(id)
        if (!batch) {
            render(view: "/404")
            return
        }
        def items = []
        def nullCount = 5 * (row - 1) + col - 1
        for (int i = 0; i < nullCount; ++i) {
            items << null
        }
        batch.cellSources.each { it ->
            items << it.item 
        }
        render(view: "/item/generateBarcodeList", model: [barcodeList: items*.barcode, nameList: items*.name, date: new Date()])
    }
    
    def deleteBatch(Long id) {
        try {
            cellSourceService.deleteBatch(id)
            flash.message = "The batch has been deleted!"
            redirect(action: "listBatches")
        } catch(Exception e) {
            flash.message = "This batch cannot be deleted!"
            redirect(action: "showBatch", id: id)
        }
    }
    
    def saveBatchNotesAjax(Long id, String notes) {
        cellSourceService.saveBatchNotes(id, notes)
        render ""
        return
    }
}

class CellSourceCommand implements grails.validation.Validateable {
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

class CellStockBatchCommand implements grails.validation.Validateable {
    List<CellSourceCommand> cellSources = [].withLazyDefault {new CellSourceCommand()}
}

class ItemBatchCommand implements grails.validation.Validateable {
    Long batchId
    List<Item> items = [].withLazyDefault {new Item()}
}