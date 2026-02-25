package pegr
import grails.gorm.transactions.Transactional
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
    def itemService
    def sequenceRunService

    def search(QuerySampleRegistrationCommand query) {
        def sample_ids = null
        if (query.treatment) {
            def t = SampleTreatments.createCriteria()
            sample_ids = t.list {
                treatment {
                    ilike "name", "%" + query.treatment + "%"
                }
                projections {
                    distinct("sample.id")
                }
            }
            if (sample_ids.size() == 0) {
                return []
            }
        } 
        
        def c = Sample.createCriteria()
        def listParams = [
                max: query.max ?: 50,
                sort: query.sort ?: "id",
                order: query.order ?: "desc",
                offset: query.offset
            ]
        def samples = c.list(listParams) {
            and {
                if (query.speciesId) {
                    cellSource {
                        strain {
                            species {
                                eq "id", query.speciesId
                            }
                        }
                    }
                } else if (query.species) {
                    cellSource {
                        strain {
                            species {
                                ilike "name", query.species
                            }
                        }
                    }
                }
                if (query.strain) {
                    cellSource {
                        strain {
                            ilike "name", query.strain
                        }
                    }
                }
                if (query.antibody) {
                    antibody {
                        ilike "catalogNumber", query.antibody
                    }
                }
                if (query.id) {
                    sqlRestriction "cast({alias}.id AS char(256)) like '${query.id}'"
                }
                if (query.sourceId) {
                    ilike "sourceId", query.sourceId
                }
                if (query.source) {
                    ilike "source", query.source
                }
                if (query.sendDataTo) {
                    sendDataTo {
                        ilike "username", query.sendDataTo
                    }                    
                }
                if (query.target) {
                    target {
                        ilike "name", query.target
                    }
                }
                if (query.assayId) {
                    assay {
                        eq "id", query.assayId
                    }
                } else if (query.assay) {
                    assay {
                        ilike "name", query.assay
                    }
                }
                if (sample_ids && sample_ids.size() > 0) {
                    'in' "id", sample_ids
                }
                if (query.status) {
                    eq "status", query.status
                }
            }
        }
        
        return samples
    }

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
        def item = sample.item
        while (item) {
            def instances = ProtocolInstanceItems.findAllByItem(item).sort{ -it.id }.collect { it.protocolInstance}
            if (instances.size()>0) {
                protocols << [item: item, protocolList: instances]
            }
            item = item.parent
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

            def growthMedia = getGrowthMedia(data.growthMedia)

            def sample = getSample(cellSource, antibody, target, data.cellNum, data.chrom, data.volume, data.requestedTags, data.sampleNotes, sendTo, abNotes, data.genomes, assay, growthMedia)

            // add treatments to sample
            if (data.treatments) {
                data.treatments.split(",").each { treatmentStr ->
                    addTreatment(sample, treatmentStr)
                }
            }
            // add index
            try {
                splitAndAddIndexToSample(sample, data.indices)
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
            sample.save(flush: true, failOnError: true)
        } catch (grails.validation.ValidationException e) {
            def errorMsg = sample.errors.fieldErrors
                .collect { it.field  }
                .join("; ")

            throw new SampleException(message: "Error in " + errorMsg)
        } catch (Exception e) {
            throw new SampleException(message: "Error saving the sample!")
        }

        if (sample.sequenceIndicesIdString != indices && sample.sequenceIndicesString != indices) {
            cleanIndices(sample)
            splitAndAddIndexToSample(sample, indices)
        }
    }

    @Transactional
    def updateProtocol(Sample sample, Long assayId, String resin, Integer pcr, Long userId, int endTime_year, int endTime_month, int endTime_day, String growthMedia, List treatments) {
        sample.assay = Assay.get(assayId)
        if (!sample.prtclInstSummary) {
            sample.prtclInstSummary = new ProtocolInstanceSummary()
        }
        sample.prtclInstSummary.user = User.get(userId)
        
        sample.prtclInstSummary.endTime = new Date(endTime_year-1900, endTime_month-1, endTime_day)
        
        def note = ['Resin':resin, 'PCR Cycle': pcr]
        sample.prtclInstSummary.note = JsonOutput.toJson(note)
        sample.prtclInstSummary.save()
        sample.growthMedia = getGrowthMedia(growthMedia)
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
        def target 
        try {
            target = antibodyService.getTarget(targetName, targetType, nterm, cterm)
        } catch(AntibodyException e) {
            throw new SampleException(message: e.message)
        }
        sample.target = target
        sample.save()
    }

    def cleanIndices(Sample sample) {
        SampleSequenceIndices.executeUpdate("delete from SampleSequenceIndices where sample.id=:sampleId", [sampleId: sample.id])
    }

    @Transactional
    def addSampleToProject(Project project, Sample sample) {
	    if(project && sample && !ProjectSamples.findByProjectAndSample(project, sample)) {
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
        if (sample == null || indexStr == null || indexStr == "") {
            return
        }
        def indexList = indexStr.split(",")*.trim()
        def setId = 1
        indexList.each { indices ->
            def indexInSet = 1
            indices.split("-")*.trim().each {
                def index = SequenceIndex.findBySequenceAndStatus(it, DictionaryStatus.Y)
                if (!index) {
                    index = SequenceIndex.findByIndexIdAndStatus(it, DictionaryStatus.Y)
                }
                if (!index) {
                    index = SequenceIndex.findBySequenceAndIndexId(it, it)
                }
                if (!index) {
                    if (it ==~ /[ACGT]+/) {
                        index = new SequenceIndex(indexId: it, sequence: it).save(failOnError: true)
                    } else {
                        throw new SampleException(message: "Index is wrong for sample ${sample.id}")
                    }
                }
                new SampleSequenceIndices(sample: sample, index: index, setId: setId, indexInSet: indexInSet).save(failOnError: true)
                indexInSet++
            }
            setId++
        }
    }

    @Transactional
    def addItem(Sample sample, Item item) {
        itemService.save(item)
        sample.item = item
        sample.save()
    }

    @Transactional
	def getGrowthMedia(String _mediaStr) {
        def mediaStr = utilityService.cleanString(_mediaStr)
	    if(mediaStr == null) {
	        return null
	    }
	    def media = GrowthMedia.findByName(mediaStr)
	    if(!media) {
	        media = new GrowthMedia(name: mediaStr).save( failOnError: true)
	    }
	    return media
	}
    
    @Transactional
    def getAssay(String _assayStr) {
        def assayStr = utilityService.cleanString(_assayStr)
	    if(assayStr == null) {
	        return null
	    }
	    
	    def assay= Assay.findByName(assayStr)
	    if(!assay) {
	        assay = new Assay(name: assayStr).save( failOnError: true)
	    }
	    return assay
	}

    def copyIndexToItem(Sample sample) {
        def item = sample.item
        ItemSequenceIndices.executeUpdate("delete from ItemSequenceIndices where item.id =:itemId", [itemId: item.id])
        if (item) {
            SampleSequenceIndices.findAllBySample(sample).each { sampleIndex ->
                new ItemSequenceIndices(item: item, index: sampleIndex.index, indexInSet: sampleIndex.indexInSet, setId: sampleIndex.setId).save()
            }
        }
    }

    @Transactional
    def delete(Sample sample) {
        if (!sample) {
            return
        }
        SampleSequenceIndices.executeUpdate("delete from SampleSequenceIndices where sample.id=:sampleId", [sampleId: sample.id])
        ProjectSamples.executeUpdate("delete from ProjectSamples where sample.id =:sampleId", [sampleId: sample.id])
        ControlSample.executeUpdate("delete from ControlSample where sample.id =:sampleId or controlSample.id =:sampleId", [sampleId: sample.id])
        PoolSamples.executeUpdate("delete from PoolSamples where sample.id =:sampleId", [sampleId: sample.id])
        ReplicateSamples.executeUpdate("delete from ReplicateSamples where sample.id =:sampleId", [sampleId: sample.id])
        SampleInRun.executeUpdate("delete from SampleInRun where sample.id =:sampleId", [sampleId: sample.id])
        SampleTreatments.executeUpdate("delete from SampleTreatments where sample.id =:sampleId", [sampleId: sample.id])

        sample.sequencingExperiments.each {
            sequenceRunService.removeExperiment(it.id)
        }
        sample.delete(failOnError: true)
    }

    /**
     * Authorization to edit the given sample: Admin or
     * the owner or participant in the project which the sample belongs to
     * @param sample the given sample
     **/
    def editAuth(Sample sample) {
        def user = springSecurityService.currentUser
        return editAuth(sample, user)
    }
    
    /**
     * Authorization to edit the given sample: Admin or
     * the owner or participant in the project which the sample belongs to
     * @param sample the given sample
     * @param user the given user
     **/
    def editAuth(Sample sample, User user) {
        if (user.isAdmin()) {
            return true
        } else {
            def sampleId = sample?.id
            def sql = new Sql(dataSource)
            
            // Count the number of projects that 
            // (1) the sample belongs to and 
            // (2) the user has a role of OWNER or PARTICIPANT in the project.
            def count = sql.rows("SELECT count(*) as cnt FROM project_user pu JOIN project_samples ps ON pu.project_id = ps.project_id WHERE pu.user_id = ${user.id} and ps.sample_id = ${sampleId} and pu.project_role in ('OWNER', 'PARTICIPANT')")
            if (count[0].cnt > 0) {
                return true
            } else {
                return false
            }
        }
    }

    @Transactional
    def update(Long sampleId, String field, String value) {
        def result
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new SampleException(message: "Sample not found!")
        }
        
        switch (field) {
            case "target" :
                def data = utilityService.parseJson(value)
                try {
                    sample.target = antibodyService.getTarget(data.name, data.type, data.nterm, data.cterm)
                } catch(AntibodyException e) {
                    throw new SampleException(message: e.message)
                }
                sample.save(failOnError: true)
                break
            case "strain" :
                def data = utilityService.parseJson(value)
                
                try {
                    sample.cellSource.strain = cellSourceService.getStrain(sample.cellSource.strain?.species, data.strain, sample.cellSource.strain?.parent?.name, data.genotype, data.geneticModification) 
                } catch(Exception e) {
                    throw new SampleException(message: e.message)
                }
            
                sample.cellSource.save(failOnError: true)
                break
            case "index" :
                if (sample.sequenceIndicesIdString != value && sample.sequenceIndicesString != value) {
                    cleanIndices(sample)
                    splitAndAddIndexToSample(sample, value)
                }
                result = [sequence: sample.sequenceIndicesString, id: sample.sequenceIndicesIdString]
                break
            case "naturalId" :
                sample.naturalId = value
                sample.save(failOnError: true)
                break
            case "genomes" :
                sample.requestedGenomes = value
                sample.save(failOnError: true)
                break
            case "pipelines" :
                sample.requestedPipelines = value
                sample.save(failOnError: true)
                break
            case "growthMedia" :
                sample.growthMedia = getGrowthMedia(value)
                sample.save(failOnError: true)
                break
            case "treatments" :
                SampleTreatments.executeUpdate("delete from SampleTreatments where sample.id =:sampleId", [sampleId: sampleId])
                def treatments = utilityService.parseJson(value)
                treatments.each { treatment ->
                    addTreatment(sample, treatment)
                }
                break
            case "sendToId" :
                def id = utilityService.getLong(value)
                sample.sendDataTo = User.get(id)
                sample.save(failOnError: true)
            case "recommend" :
                sample.recommend = value
                sample.save(faileOnError: true)
                break
            case "assay" :
                sample.assay = getAssay(value)
                sample.save(faileOnError: true)
                break
            case "geoAccession" :
                sample.geoAccession = value
                sample.save(failOnError: true)
                break
            default:
                sample[field] = utilityService.getFloat(value)
                sample.save(failOnError: true)
                break
        }
        return result
    }
    
    /**
    * Update sample's status.
    * @param sampleId the sample's ID
    * @param statusStr the new status string
    */
    @Transactional
    def updateSampleStatus(Long sampleId, String statusStr) {
        def sample = Sample.get(sampleId)
        if (!sample) {
            throw new SampleException(message: "Sample not found!")
        }

        try {
            SampleStatus status = statusStr as SampleStatus
            sample.status = status
            sample.save()
        } catch(Exception e) {
            throw new SampleException(message: "Wrong sample status!")
        }

    } 
}
