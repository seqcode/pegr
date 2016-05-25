package pegr
import grails.transaction.Transactional

class SampleService {
    def antibodyService
    def utilityService
    
    @Transactional
    saveNewSamples(Long assayId, Long projectId, List samples) {
        // get assay
        def assay = Assay.get(assayId)
        if (!assay) {
            throw new ProjectException(message: "Assay not found!")
        }
        // get project
        def project = Project.get(projectId)
        if (!project) {
            throw new ProjectException(message: "Project not found!")
        }
        samples.each { data ->
            def provider = User.get(data.providerId)
            def sendTo = User.get(data.sendToId)
            def species = getSpecies(data.genus, data.speciesId)
            def strain = getStrain(species, data.strain, data.parentStrain, data.genotype, data.mutation)
            def tissue = getTissue(data.tissue)
            def prepUser = User.get(data.prepUserId)
            def growthMedia = getGrowthMedia(data.growthMediaId, species)        
            
            // save cell source
            def cellSource = getCellSource(prepUser, growthMedia, strain, provider, tissue)
            data.treatmentId.each { treatmentStr ->
                addTreatment(cellSource, treatmentStr)
            }
            // save the antibody
            def antibody = antibodyService.getAntibody(data.company,  data.catalogNumber, data.lotNumber, data.abNotes, data.clonal, data.abHostId, data.igTypeId, data.immunogene, data.abConcentration)
                
            // save the target
            def target = antibodyService.getTarget(data.target, data.targetTypeId, data.nterm, data.cterm)
                
            // put requested genomes into sample notes
            def requestedGenomes = data.genomeId?.join(",")
            
            // save sample
            def sample = getSample(cellSource, antibody, target, data.cellNum, data.chrom, data.volume, data.requestedTags, data.sampleNotes, sendTo, data.abNotes, requestedGenomes, assay)
            
            // add sample to the project
            addSampleToProject(project, sample)
        }
    }
    
    @Transactional
    def addSampleToProject(Project project, Sample sample) {
	    if(project && sample) {
	        new ProjectSamples(project: project, sample: sample).save( failOnError: true)
	    }
	}
    
    @Transactional
    def getStrain(Species species, String strainStr, String parentStrainStr, String genotypeStr, String mutationStr) {  
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
    def getTissue(String tissueStr) {
        if (tissueStr == null) {
            return null
        }
        if (tissueStr.isInteger()) {
            return Tissue.get(tissueStr.toInteger())
        }
        def tissue = Tissue.findByName(tissueStr)
        if (!tissue) {
            tissue = new Tissue(name: tissueStr).save( failOnError: true)
        }
        return tissue
    }
    
    @Transactional
	def getSpecies(String genusStr, String speciesStr) {
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
	def getGrowthMedia(String mediaStr, Species species) {
	    if(mediaStr == null) {
	        return null
	    }
        if (mediaStr.isInteger()) {
            return GrowthMedia.get(mediaStr.toInteger())
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
	def getCellSource(User prepUser, GrowthMedia growthMedia, Strain strain, User provider, Tissue tissue) {
	    if (!strain) {
	        return null
	    }		
	    def cellSource = new CellSource(providerUser: provider, strain: strain, growthMedia: growthMedia, prepUser: prepUser,  tissue: tissue).save( failOnError: true)
	    return cellSource
	}
	
	def addTreatment(CellSource cellSource, String treatmentStr) {
	    if(treatmentStr == null) {
	        return null
	    }
        if (treatmentStr.isInteger()) {
            return CellSourceTreatment.get(treatmentStr.toInteger())
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
    
    @Transactional
    def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, User dataTo, String abNotes, String requestedGenomes, Assay assay) {
        def now = new Date()
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: utilityService.getFloat(requestedTagNum), chromosomeAmount: utilityService.getFloat(chromAmount), cellNumber: utilityService.getFloat(cellNum), volume: utilityService.getFloat(volume), note: sampleNotes, status: SampleStatus.CREATED, date: now, sendDataTo: dataTo, antibodyNotes: abNotes, requestedGenomes: requestedGenomes, assay: assay).save(failOnError: true)
	    return sample
	}
    
}