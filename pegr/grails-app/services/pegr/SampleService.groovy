package pegr
import grails.transaction.Transactional
import groovy.json.*
import groovy.sql.Sql
    
class SampleException extends RuntimeException {
    String message
}

class SampleService {
    def dataSource
    def springSecurityService
    def antibodyService
    def utilityService
    def cellSourceService
    def replicateService
    
    def getSampleDetails(Sample sample) {
        def jsonSlurper = new JsonSlurper()
        def notes = [:]
        try {
            notes += jsonSlurper.parseText(sample.prtclInstSummary.note)
        }catch(Exception e){

        }       
        try {
            notes += jsonSlurper.parseText(sample.antibodyNotes)
        }catch(Exception e){

        }
        def protocols = []
        sample.bags.each{ linkedbag ->
            protocols.push([bag:linkedbag, protocolList:ProtocolInstance.where{bag.id == linkedbag.id}.list(sort: "bagIdx", order: "asc")])
        }
        def replicates = replicateService.getReplicates(sample)
        return [sample: sample, notes: notes, protocols: protocols, replicates: replicates] 
    }
    
   /**
    * Save a list of new samples that belong to the sample project and use the same assay.
    * @param assayId assay's ID
    * @param projectId project's ID
    * @param samples a list of samples
    * @return message message about wrong indices
    */
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
            def sendTo = User.get(data.sendToId)
            
            // save the cell source
            def cellSource = cellSourceService.getCellSource(data)
            
            // save the antibody
            def antibody = antibodyService.getAntibody(data.company,  data.catalogNumber, data.lotNumber, data.abNotes, data.clonal, data.abHost, data.igType, data.immunogene, data.abConcentration)
                
            // save the target
            def target = antibodyService.getTarget(data.target, data.targetType, data.nterm, data.cterm)
            
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
            
            def growthMedia = getGrowthMedia(data.growthMedia, cellSource.strain?.species)
            
            def sample = getSample(cellSource, antibody, target, data.cellNum, data.chrom, data.volume, data.requestedTags, data.sampleNotes, sendTo, abNotes, data.genomes, assay, growthMedia)
                    
            // add treatments to sample
            if (data.treatments) {
                data.treatments.split(",").each { treatmentStr ->
                    addTreatment(sample, treatmentStr)
                }
            }
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
    
    def addTreatment(Sample sample, String _treatmentStr) {
        def treatmentStr = utilityService.cleanString(_treatmentStr)
	    if(treatmentStr == null) {
	        return null
	    }
        def name = treatmentStr
        def description = treatmentStr
        if(treatmentStr.size() > 250) {
            name = treatmentStr.take(250)
        }
	    def treatment = CellSourceTreatment.findByName(name)
	    if(!treatment) {
	        treatment = new CellSourceTreatment(name: name, note: description).save(failOnError: true)
	    }
	    if(treatment && sample) {
            if (!SampleTreatments.findBySampleAndTreatment(sample, treatment)) {
                new SampleTreatments(sample: sample, treatment: treatment).save( failOnError: true)
            }			
		} 
	}
    
    @Transactional
    def updateOther(Sample sample, String indexType, String indices) {
        try {
            sample.save()
        } catch (Exception e) {
            throw new SampleException(message: "Error saving the sample!")
        }
        
        if (indexType == "ID") {  
            if (sample.sequenceIndicesIdString != indices) {
                cleanIndices(sample)
                splitIdAndAddIndexToSample(sample, indices)
            }            
        } else {
            if (sample.sequenceIndicesString != indices) {
                cleanIndices(sample)
                splitAndAddIndexToSample(sample, indices)
            }
        }               
    }
    
    @Transactional
    def updateProtocol(Sample sample, Long assayId, String resin, Integer pcr, Long userId, String endTime, String growthMedia, List treatments) {
        sample.assay = Assay.get(assayId)
        if (!sample.prtclInstSummary) {
            sample.prtclInstSummary = new ProtocolInstanceSummary()
        }
        sample.prtclInstSummary.user = User.get(userId)
        sample.prtclInstSummary.endTime = Date.parse("E MMM dd H:m:s z yyyy", endTime)
        def note = ['Resin':resin, 'PCR Cycle': pcr]
        sample.prtclInstSummary.note = JsonOutput.toJson(note)
        sample.prtclInstSummary.save()
        sample.growthMedia = getGrowthMedia(growthMedia, sample.cellSource?.strain?.species)
        sample.save()
        
        // save cell source's treatments
        def toDelete = SampleTreatments.where{ sample == sample}.list()
        if (treatments) {
            treatments.each { treatmentName ->
                def oldTreatment = toDelete.find{it.treatment.name == treatmentName}
                if (oldTreatment) {
                    toDelete.remove(oldTreatment)
                } else {
                    def treatment = CellSourceTreatment.findByName(treatmentName)
                    if (!treatment) {
                        treatment = new CellSourceTreatment(name: treatmentName).save(failOnError: true)
                    }
                    new SampleTreatments(sample: sample, treatment: treatment).save()
                }
            }
        }
        toDelete.each {
            it.delete()
        }
    } 
    
    @Transactional
    def updateTarget(Sample sample, String targetName, String targetType, String nterm, String cterm) {
        def target = antibodyService.getTarget(targetName, targetType, nterm, cterm)
        sample.target = target
        sample.save()
    }
    
    def cleanIndices(Sample sample) {
        SampleSequenceIndices.executeUpdate("delete from SampleSequenceIndices where sample.id=?", [sample.id])
    }
    
    @Transactional
    def addSampleToProject(Project project, Sample sample) {
	    if(project && sample) {
	        new ProjectSamples(project: project, sample: sample).save( failOnError: true)
	    }
	}
    
    
    @Transactional
    def getSample(CellSource cellSource, Antibody antibody, Target target, String cellNum, String chromAmount, String volume, String requestedTagNum, String sampleNotes, User dataTo, String abNotes, String requestedGenomes, Assay assay, GrowthMedia growthMedia) {
        def now = new Date()
	    def sample = new Sample(cellSource: cellSource, antibody: antibody, target: target, requestedTagNumber: utilityService.getFloat(requestedTagNum), chromosomeAmount: utilityService.getFloat(chromAmount), cellNumber: utilityService.getFloat(cellNum), volume: utilityService.getFloat(volume), note: sampleNotes, status: SampleStatus.CREATED, date: now, sendDataTo: dataTo, antibodyNotes: abNotes, requestedGenomes: requestedGenomes, assay: assay, growthMedia: growthMedia).save(failOnError: true)
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
                def index = SequenceIndex.findByIndexIdAndStatus(it, DictionaryStatus.Y)
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
    
    /**
     * Authorization to edit the given sample: Admin or 
     * the owner or participant in the project which the sample belong to
     * @param sample the given sample
     **/
    def editAuth(Sample sample) {
        def user = springSecurityService.currentUser
        if (user.isAdmin()) {
            return true
        } else {
            def sampleId = sample?.id
            def sql = new Sql(dataSource)
            def count = sql.rows("SELECT count(*) as cnt FROM project_user pu JOIN project_samples ps ON pu.project_id = ps.project_id WHERE pu.user_id = ${user.id} and ps.sample_id = ${sampleId} and pu.project_role in (${ProjectRole.OWNER}, ${ProjectRole.PARTICIPANT})") 
            if (count[0].cnt > 0) {
                return true
            } else {
                return false
            }                    
        }          
    }
}