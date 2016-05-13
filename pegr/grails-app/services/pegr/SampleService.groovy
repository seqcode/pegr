package pegr
import grails.transaction.Transactional

class SampleService {
    @Transactional
    def addSampleToProject(Project project, Sample sample) {
	    if(project && sample) {
	        new ProjectSamples(project: project, sample: sample).save( failOnError: true)
	    }
	}
    
    def saveStrain(Species species, String strainStr, String parentStrainStr, String genotypeStr, String mutationStr) {  
        // get the parent strain and tissue
        def parentStrain = null
        def parentTissue = getTissue(parentStrainStr)
        if (parentStrainStr && !parentTissue) {
            parentStrain = Strain.findByName(parentStrainStr)
            if (!parentStrain) {
                parentStrain = new Strain(name: parentStrainStr, species: species).save(failOnError: true) 
            }
        }
        
        // get strain and tissue
        def strain = null
        def tissue = getTissue(strainStr)
        if (!tissue) {
            tissue = parentTissue
			if (strainStr || parentStrain || genotypeStr || mutationStr) {
	            strain = Strain.findByNameAndParentAndGenotypeAndGeneticModification(strainStr, parentStrain, genotypeStr, mutationStr)
	            if (!strain) {
	                strain = new Strain(name: strainStr, 
	                                    species: species, 
	                                    genotype: genotypeStr, 
	                                    parent: parentStrain, 
	                                    geneticModification: mutationStr).save( failOnError: true)
	            }
			}
        }
        
        return [strain: strain, tissue: tissue]	    
	}
	
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
	
	
	def getCellSource( String samplePrepUser, GrowthMedia growthMedia, Strain strain, User provider, Inventory inventory, Tissue tissue) {
	    if (!strain) {
	        return null
	    }
		def prepUser = getUser(samplePrepUser)
	    def cellSource = new CellSource(providerUser: provider, strain: strain, growthMedia: growthMedia, prepUser: prepUser, inventory: inventory, tissue: tissue).save( failOnError: true)
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
    
    def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, String sampleId, String bioRep1SampleId, String bioRep2SampleId, Invoice invoice, User dataTo, String dateStr, ProtocolInstanceSummary prtcl, String seqId, String abNotes) {
	    def note = [:]
	    if (sampleNotes) {
	        note['note'] = sampleNotes
	    }
        if (sampleId) {
            note['sampleId'] = sampleId
        }
	    if (bioRep1SampleId) {
	        note['bioRep1'] = bioRep1SampleId
	    }
	    if (bioRep2SampleId) {
	        note['bioRep2'] = bioRep2SampleId
	    }
	    def date = getDate(dateStr)
        def source = "PughLab"
        if (invoice.invoiceNum && invoice.invoiceNum.size() >= 4 && invoice.invoiceNum[1..3] ==~ /\d+/ ) {
            switch (invoice.invoiceNum[0].toLowerCase()) {
                case "p":
                    source = "Peconic"
                    break
                case "x":
                    source = "CustomerX"
                    break
                case "s":
                    source = "CustomerS"
                    break
            }
        }
        
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: getFloat(requestedTagNum), chromosomeAmount: getFloat(chromAmount), cellNumber: getFloat(cellNum), volume: getFloat(volume), note: JsonOutput.toJson(note), status: SampleStatus.COMPLETED, date: date, sendDataTo: dataTo, invoice: invoice, prtclInstSummary: prtcl, sourceId: seqId, source: source, antibodyNotes: abNotes).save( failOnError: true)
	    return sample
	}
    
    
}