package pegr
import grails.transaction.Transactional

class SampleService {
    @Transactional
    saveNewSamples(Long assayId, Long projectId, SampleListCommand samples) {
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
            // save cell source
            def prepUser = User.get(data.prepUserId)
            def growthMedia
            def strain
            def provider = User.get(data.providerId)
            def tissue
            def cellSource = getCellSource(prepUser, growthMedia, strain, provider, tissue)
            // save sample
            def dataTo = User.get(data.sendToId)
            def sample = getSample(cellSource, antibody, target, cellNum, chromAmount, volume, requestedTagNum, sampleNotes, dataTo, abNotes)
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
        parentStrain = Strain.findByName(parentStrainStr)
        if (!parentStrain) {
                parentStrain = new Strain(name: parentStrainStr, species: species).save(failOnError: true) 
        }
        // get strain and tissue        
        strain = Strain.findByNameAndParentAndGenotypeAndGeneticModification(strainStr, parentStrain, genotypeStr, mutationStr)
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
	
	def addTreatment(CellSource cellSource, String perturbStr) {
	    if(perturbStr == null) {
	        return null
	    }
	    def perturbation = CellSourceTreatment.findByName(perturbStr)
	    if(!perturbation) {
	        perturbation = new CellSourceTreatment(name: perturbStr).save(failOnError: true)
	    }
	    if(perturbation && cellSource) {
            if (!CellSourceTreatments.findByCellSourceAndTreatment(cellSource, perturbation)) {
                new CellSourceTreatments(cellSource: cellSource, treatment: perturbation).save( failOnError: true)
            }			
		} 
	}
    
    @Transactional
    def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, User dataTo, String abNotes) {
        def now = new Date()
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: getFloat(requestedTagNum), chromosomeAmount: getFloat(chromAmount), cellNumber: getFloat(cellNum), volume: getFloat(volume), note: sampleNotes, status: SampleStatus.CREATED, date: now, sendDataTo: dataTo, antibodyNotes: abNotes).save(failOnError: true)
	    return sample
	}
    
    
}