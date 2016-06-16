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
    def cellSourceService
    
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
    
}