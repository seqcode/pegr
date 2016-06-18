package pegr

import grails.transaction.Transactional

class CellSourceException extends RuntimeException {
    String message
}

class CellSourceService {
    def itemService
    def utilityService
    
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
                growthMedia = getGrowthMedia(cmd.growthMedia, species)
                age = utilityService.cleanString(cmd.age)
                biologicalSourceId = utilityService.cleanString(cmd.bioSourceId)
                providerUser = User.get(cmd.providerId)
                providerLab = Lab.get(cmd.providerLabId)
            }
            
            // save cell source
            if (!cellSource.save(flush: true)) {
                throw new ItemException(message: "Invalid inputs for cell source!")
            }
            // save cell source's treatments
            def toDelete = CellSourceTreatments.where{cellSource == cellSource}.list()
            if (cmd.treatments) {
                cmd.treatments.split(",").each { treatmentName ->
                    def oldTreatment = toDelete.find{it.treatment.name == treatmentName}
                    if (oldTreatment) {
                        toDelete.remove(oldTreatment)
                    } else {
                        def treatment = CellSourceTreatment.findByName(treatmentName)
                        if (!treatment) {
                            treatment = new CellSourceTreatment(name: treatmentName).save(failOnError: true)
                        }
                        new CellSourceTreatments(cellSource: cellSource, treatment: treatment).save()
                    }
                }
            }
            toDelete.each {
                it.delete()
            }
        }
    }       
    
    @Transactional
    def save(Item item, CellSourceCommand cmd){
        itemService.save(item) 
        def cellSource = getCellSource(cmd)
        cellSource.item = item 
        cellSource.save()
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
	def getGrowthMedia(String _mediaStr, Species species) {
        def mediaStr = utilityService.cleanString(_mediaStr)
	    if(mediaStr == null) {
	        return null
	    }
	    def media = GrowthMedia.findByName(mediaStr)
	    if(!media) {
	        media = new GrowthMedia(name: mediaStr, species: species).save( failOnError: true)
	    } else {
	        if(media.species != species) {
	            media.species = null
				media.save( failOnError: true)
	        }
	    }
	    return media
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
        def growthMedia = getGrowthMedia(data.growthMedia, species)                
        def age = utilityService.cleanString(data.age)
        def bioSourceId = utilityService.cleanString(data.bioSourceId)
        
        // save cell source
	    def cellSource = new CellSource(providerUser: provider, providerLab: providerLab, biologicalSourceId: bioSourceId, strain: strain, growthMedia: growthMedia, tissue: tissue, age: age, sex: sex, histology: histology).save( failOnError: true)
        
        // add treatments to cell source
        if (data.treatments) {
            data.treatments.split(",").each { treatmentStr ->
                addTreatment(cellSource, treatmentStr)
            }
        }

	    return cellSource
	}
	
	def addTreatment(CellSource cellSource, String _treatmentStr) {
        def treatmentStr = utilityService.cleanString(_treatmentStr)
	    if(treatmentStr == null) {
	        return null
	    }
	    def treatment = CellSourceTreatment.findByName(treatmentStr)
	    if(!treatment) {
	        treatment = new CellSourceTreatment(name: treatmentStr).save(failOnError: true)
	    }
	    if(treatment && cellSource) {
            if (!CellSourceTreatments.findByCellSourceAndTreatment(cellSource, treatment)) {
                new CellSourceTreatments(cellSource: cellSource, treatment: treatment).save( failOnError: true)
            }			
		} 
	}

}
