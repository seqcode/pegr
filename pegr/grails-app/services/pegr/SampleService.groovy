package pegr
import grails.transaction.Transactional
import groovy.json.*
    
class SampleException extends RuntimeException {
    String message
}

class SampleService {
    def springSecurityService
    def antibodyService
    def utilityService
    
    @Transactional
    def saveNewSamples(Long assayId, Long projectId, List samples) {
        def message = "Samples saved! "
        // get assay
        def assay = Assay.get(assayId)
        if (!assay) {
            throw new SampleException(message: "Assay not found!")
        }
        // get project
        def project = Project.get(projectId)
        if (!project) {
            throw new SampleException(message: "Project not found!")
        }
        samples.each { data ->
            def provider = User.get(data.providerId)
            def sendTo = User.get(data.sendToId)
            def species = getSpecies(data.genus, data.speciesId)
            def strain = getStrain(species, data.strain, data.parentStrain, data.genotype, data.mutation)
            def tissue = getTissue(data.tissue)
            def growthMedia = getGrowthMedia(data.growthMediaId, species)        
            
            // save cell source
            def cellSource = getCellSource(growthMedia, strain, provider, tissue)
            if (data.treatments) {
                data.treatments.split(",").each { treatmentStr ->
                    addTreatment(cellSource, treatmentStr)
                }
            }
            // save the antibody
            def antibody = antibodyService.getAntibody(data.company,  data.catalogNumber, data.lotNumber, data.abNotes, data.clonal, data.abHostId, data.igTypeId, data.immunogene, data.abConcentration)
                
            // save the target
            def target = antibodyService.getTarget(data.target, data.targetTypeId, data.nterm, data.cterm)
            
            // save sample
            def abnoteMap = [:]
            if (data.abVolumePerSample) {
                abnoteMap['Volume Sent (ul)'] = data.abVolumePerSample
            }
            if (data.ugPerChip) {
                abnoteMap['Usage Per ChIP (ug)'] = data.ugPerChip
            }
            if (data.ulPerChip) {
                abnoteMap['Usage Per ChIP (ul)'] = data.ulPerChip
            }

            def abNotes = JsonOutput.toJson(abnoteMap)
            def sample = getSample(cellSource, antibody, target, data.cellNum, data.chrom, data.volume, data.requestedTags, data.sampleNotes, sendTo, abNotes, data.genomes, assay)
            
            // add index
            try {
                if (data.indexType == "ID") {
                    splitIdAndAddIndexToSample(sample, data.indices)
                } else {
                    splitAndAddIndexToSample(sample, data.indices)
                }
            } catch(SampleException e) {
                message += "Index is not added correctly to sample ${sample.id}! "
            }
            // add sample to the project
            addSampleToProject(project, sample)
        }
        return message
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
	def getCellSource(GrowthMedia growthMedia, Strain strain, User provider, Tissue tissue) {
	    if (!strain) {
	        return null
	    }		
	    def cellSource = new CellSource(providerUser: provider, strain: strain, growthMedia: growthMedia, tissue: tissue).save( failOnError: true)
	    return cellSource
	}
	
	def addTreatment(CellSource cellSource, String treatmentStr) {
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
    
    @Transactional
    def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, User dataTo, String abNotes, String requestedGenomes, Assay assay) {
        def now = new Date()
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: utilityService.getFloat(requestedTagNum), chromosomeAmount: utilityService.getFloat(chromAmount), cellNumber: utilityService.getFloat(cellNum), volume: utilityService.getFloat(volume), note: sampleNotes, status: SampleStatus.CREATED, date: now, sendDataTo: dataTo, antibodyNotes: abNotes, requestedGenomes: requestedGenomes, assay: assay).save(failOnError: true)
	    return sample
	}
    
    @Transactional
    def splitAndAddIndexToSample(Sample sample, String indexStr) {
        if (sample == null || indexStr == null) {
            return
        }
        def indexList = indexStr.split(",")*.trim()
        def setId = 1
        indexList.each { indices ->
            def indexInSet = 1
            indices.split("-")*.trim().each {
                def index = SequenceIndex.findBySequenceAndStatus(it, DictionaryStatus.Y)
                if (!index) {
                    throw new SampleException(message: "Incorrect index ${it}!")
                }
                new SampleSequenceIndices(sample: sample, index: index, setId: setId, indexInSet: indexInSet).save(failOnError: true)
                indexInSet++
            }
            setId++
        }
    }
    
    @Transactional
    def splitIdAndAddIndexToSample(Sample sample, String indexStr) {
        if (sample == null || indexStr == null) {
            return
        }
        def indexList = indexStr.split(",")*.trim()
        def setId = 1
        indexList.each { indices ->
            def indexInSet = 1
            indices.split("-")*.trim().each {
                def index = SequenceIndex.findBySequenceIndexAndStatus(it, DictionaryStatus.Y)
                if (!index) {
                    throw new SampleException(message: "Incorrect index ${it}!")
                }
                new SampleSequenceIndices(sample: sample, index: index, setId: setId, indexInSet: indexInSet).save(failOnError: true)
                indexInSet++
            }
            setId++
        }
    }
    
    @Transactional
    def addItem(Sample sample, Item item) {
        if (!item?.type) {
            throw new SampleException(message: "Missing item type!")
        }
        if (!item?.barcode) {
            throw new SampleException(message: "Missing barcode!")
        }
        if (Item.where { type.id == item.type.id && barcode == item.barcode}.find()) {
            throw new SampleException(message: "Barcode ${item.barcode} has been used! Please choose another barcode.")
        }
        item.user = springSecurityService.currentUser
        item.save()
        sample.item = item
        sample.save()
    }
    
    def getReplicates(Sample sample) {
        def replicateSets = ReplicateSamples.findAllBySample(sample).collect{it.set}
        def bioReps = replicateSets.findAll {it.type == ReplicateType.BIOLOGICAL}
        def techReps = replicateSets.findAll {it.type == ReplicateType.TECHNICAL}
        [bioReps: bioReps, techReps: techReps]
    }
}