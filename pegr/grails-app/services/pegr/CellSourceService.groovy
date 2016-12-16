package pegr

import com.opencsv.CSVParser
import com.opencsv.CSVReader
import grails.transaction.Transactional

class CellSourceException extends RuntimeException {
    String message
}

class CellSourceService {
    def itemService
    def utilityService
    def springSecurityService
    
    @Transactional
    def update(CellSourceCommand cmd) {
        def cellSource = CellSource.get(cmd.cellSourceId)
        if (cellSource) {
            def species = getSpecies(cmd.genus, cmd.speciesId)
            cellSource.with {
                strain = getStrain(species, cmd.strain, cmd.parentStrain, cmd.genotype, cmd.mutation)
                tissue = getTissue(cmd.tissue)
                sex = getSex(cmd.sex)
                histology = getHistology(cmd.histology)
                age = utilityService.cleanString(cmd.age)
                biologicalSourceId = utilityService.cleanString(cmd.bioSourceId)
                providerUser = User.get(cmd.providerId)
                providerLab = Lab.get(cmd.providerLabId)
            }
            
            // save cell source
            if (!cellSource.save(flush: true)) {
                throw new CellSourceException(message: "Invalid inputs for cell source!")
            }
        }
        return cellSource
    }       
    
    @Transactional
    def update(CellSourceCommand cmd, Item item){
        def cellSource = update(cmd)
        if (!item.type && !item.barcode) {
            return
        }
        if (cellSource.item) {
            try {
                cellSource.item.properties = item
                cellSource.item.save()        
            } catch (Exception e) {
                log.error e
                throw new CellSourceException(message: "Error saving the item!")
            }
        } else {
            try {
                itemService.save(item)
            } catch (ItemException e) {
               throw new CellSourceException(message: e.message)
            }
            cellSource.item = item
            cellSource.save()
        }
    }
    
    @Transactional
    def save(Item item, CellSourceCommand cmd){
        try {
            itemService.save(item) 
        } catch (ItemException e) {
           throw new CellSourceException(message: e.message)
        }
        def cellSource = getCellSource(cmd)
        cellSource.item = item 
        cellSource.status = DictionaryStatus.Y
        cellSource.save()
        return cellSource
    }
    
    @Transactional
    def saveInSample(Long sampleId, CellSourceCommand cmd, Item item) {
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new CellSourceException(message: "Sample not found!")
        }
        def cellSource = save(item, cmd)
        sample.cellSource = cellSource
        sample.save()
    }
    
    @Transactional
    def addCellSourceToSample(Long sampleId, Long itemId) {
        def sample = Sample.get(sampleId)
        def cellSource = CellSource.where {item.id == itemId}.get(max: 1)
        if (!sample) {
            throw new CellSourceException(message: "Sample not found!")
        }
        if (!cellSource) {
            throw new CellSourceException(message: "Cell Source not found!")
        }
        if (sample && cellSource) {
            sample.cellSource = cellSource
            sample.save()
        } 
    }
    
    @Transactional
    def getStrain(Species species, String _strainStr, String _parentStrainStr, String _genotypeStr, String _mutationStr) {  
        // clean input strings
        def parentStrainStr = utilityService.cleanString(_parentStrainStr)
        def strainStr = utilityService.cleanString(_strainStr)
        def genotypeStr = utilityService.cleanString(_genotypeStr)
        def mutationStr = utilityService.cleanString(_mutationStr)
        
        // get the parent strain        
        def parentStrain = Strain.findByName(parentStrainStr)
        if (!parentStrain) {
                parentStrain = new Strain(name: parentStrainStr, species: species).save(failOnError: true) 
        }
        // get strain    
        def strain = Strain.findByNameAndParentAndGenotypeAndGeneticModification(strainStr, parentStrain, genotypeStr, mutationStr)
        if (!strain) {
            strain = new Strain(name: strainStr, 
                                species: species, 
                                genotype: genotypeStr, 
                                parent: parentStrain, 
                                geneticModification: mutationStr).save( failOnError: true)
        }
        return strain
	}
	
    @Transactional
    def getTissue(String _tissueStr) {
        def tissueStr = utilityService.cleanString(_tissueStr)
        if (tissueStr == null) {
            return null
        }
        def tissue = Tissue.findByName(tissueStr)
        if (!tissue) {
            tissue = new Tissue(name: tissueStr).save( failOnError: true)
        }
        return tissue
    }
    
    @Transactional
    def getSex(String _sexStr) {
        def sexStr = utilityService.cleanString(_sexStr)
        if (sexStr == null) {
            return null
        }
        def sex = Sex.findByName(sexStr)
        if (!sex) {
            sex = new Sex(name: sexStr).save(failOnError: true)
        }
        return sex
    }
    
    @Transactional
    def getHistology(String _histologyStr) {
        def histologyStr = utilityService.cleanString(_histologyStr)
        if (histologyStr == null) {
            return null
        }
        def histology = Histology.findByName(histologyStr)
        if (!histology) {
            histology = new Histology(name: histologyStr).save(failOnError: true)
        }
        return histology
    }
    
    @Transactional
	def getSpecies(String _genusStr, String _speciesStr) {
        def genusStr = utilityService.cleanString(_genusStr)
        def speciesStr = utilityService.cleanString(_speciesStr)

	    if(genusStr == null && speciesStr == null) {
	        return null
	    }
		if (genusStr == null) {
			genusStr = "Unknown"
		}
		if (speciesStr == null) {
			speciesStr = "Unknown"
		}

        if (speciesStr.isInteger()) {
            return Species.get(speciesStr.toInteger())
        }
        
	    def species = Species.findByNameAndGenusName(speciesStr, genusStr)
	    if(!species) {
	        species = new Species(name: speciesStr, genusName: genusStr).save( failOnError: true)
	    }
	    return species
	}
    
    @Transactional
	def getCellSource(def data) {
        
        def provider = User.get(data.providerId)
        def providerLab = Lab.get(data.providerLabId)

        def species = getSpecies(data.genus, data.speciesId)
        def strain = getStrain(species, data.strain, data.parentStrain, data.genotype, data.mutation)
        def tissue = getTissue(data.tissue)
        def sex = getSex(data.sex)
        def histology = getHistology(data.histology)
             
        def age = utilityService.cleanString(data.age)
        def bioSourceId = utilityService.cleanString(data.bioSourceId)
        
        // save cell source
	    def cellSource = new CellSource(providerUser: provider, providerLab: providerLab, biologicalSourceId: bioSourceId, strain: strain, tissue: tissue, age: age, sex: sex, histology: histology).save( failOnError: true)

	    return cellSource
	}

    @Transactional
    def batchSave(List cellSourceCmds) {
        def cellSources = []
        cellSourceCmds.each { cmd ->
            def cellSource = getCellSource(cmd)
            cellSource.status = DictionaryStatus.Y
            cellSource.save()
            cellSources.push(cellSource)
        }
        def batch = createBatch(cellSources)
        return batch
    }
    
    @Transactional
    def updateItem(Long cellSourceId, Item item) {        
        def cellSource = CellSource.get(cellSourceId)
        if (cellSource) {
            try {
                if (cellSource.item) {
                    cellSource.item.properties['name', 'type', 'barcode', 'location', 'notes'] = item
                    itemService.save(cellSource.item)
                } else {
                    itemService.save(item)
                    cellSource.item = item
                    cellSource.save()
                }
            } catch (ItemException e) {
                throw new CellSourceException(message: e.message)
            }
        } else {
            throw new CellSourceException(message: "Cell stock not found!")
        }
    }
    
    @Transactional
    def delete(Long cellSourceId) {
        def cellSource = CellSource.get(cellSourceId)
        if (!cellSource) {
            throw new CellSourceException(message: "Cell stock not found!")
        }
        
        try {
            cellSource.delete()
            def item = cellSource.item
            if (item) {
                item.delete()
            }
        } catch (Exception e) {
            throw new CellSourceException(message: "Cell stock cannot be removed!")
        }
    }
    
    @Transactional
    def importCellStock(String filename, int startLine, int endLine) {
        def lineNo = 0    
        def file = new FileReader(filename)
        CSVReader reader = new CSVReader(file)
        String [] rawdata
        def cellSources = []

        while ((rawdata = reader.readNext()) != null) {
            ++lineNo
            if (lineNo < startLine) {
                continue
            } else if (endLine > 0 && lineNo > endLine) {
                break
            }
            try {
                def data = getCellStockData(rawdata)
                def species = Species.findByGenusNameAndName(data.genus, data.species)
                def parent = Strain.findBySpeciesAndNameAndGenotypeAndGeneticModification(species, data.parent, null, null)
                if (!parent) {
                    parent = new Strain(species: species, name: data.parent)
                    parent.save(failOnError: true)
                }
                def strain = Strain.findBySpeciesAndNameAndParentAndGenotypeAndGeneticModification(species, data.strain, parent, null, null)
                if (!strain) {
                    strain = new Strain(name: data.strain, 
                                        species: species, 
                                        parent: parent).save( failOnError: true)
                }
                def cellSource = new CellSource(strain: strain, status: DictionaryStatus.Y).save()
                cellSources.push(cellSource)
            }catch(Exception e) {
                log.error "Error: line ${lineNo}. " + e
                throw new CellSourceException(message: "Error: line ${lineNo}.")
            }   
        }
        def batch = createBatch(cellSources)
        return batch
    }
    
    @Transactional
    def createBatch(List cellSources) {
        if (cellSources.size() > 0) {
            def batch = new CellSourceBatch(user: springSecurityService.currentUser, 
                                            date: new Date())
            batch.save()
            cellSources.each {cs ->
                new BatchCellSources(batch: batch, cellSource: cs).save(flush: true, failOnError: true)
            }
            return batch
        } else {
            return null
        }        
    }

    def getCellStockData(String[] data) {
        [genus: data[0],         
         species: data[1],
         parent: data[2],
         strain: data[3]
        ]
    }
    
    @Transactional
    def saveItems(ItemBatchCommand cmd) {
        def batch = CellSourceBatch.get(cmd.batchId)
        def cellSources =  batch.cellSources
        cmd.items.eachWithIndex {item, index->
            itemService.save(item)
            cellSources[index].item = item
            cellSources[index].save()
        }
    }
    
    @Transactional
    def deleteBatch(Long id) {
        def batch = CellSourceBatch.get(id)
        def cellSources = batch.cellSources
        BatchCellSources.executeUpdate("delete from BatchCellSources where batch.id =:batchId", [batchId: id])
        cellSources.each { it ->
            it.item.delete()
            it.delete()
        }
        batch.delete()
    }
}
