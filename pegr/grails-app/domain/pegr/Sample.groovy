package pegr

class Sample {
		
	CellSource cellSource
	Antibody antibody
    String antibodyNotes
	Target target
	Double requestedTagNumber
	Double chromosomeAmount // in ug
	Double cellNumber // in M
	Double volume //ul per aliqu used for the assay
	String publicationReference
    SampleStatus status
    Date date
	CellSource spikeInCellSource
	String note
    User sendDataTo
    Invoice invoice
	ProtocolInstanceSummary prtclInstSummary
    Item item
    String source
    String sourceId
    Assay assay
    String requestedGenomes
    String recommend
    GrowthMedia growthMedia
    String naturalId 
    
    List getRuns() {
        return SequencingExperiment.where{sample == this}.collect{it.sequenceRun}
    }
    
    List getSequencingExperiments() {
        return SequencingExperiment.where{sample == this}.list()
    }
    
    String getSequenceIndicesString() {
        def indexDict = SampleSequenceIndices.where{sample == this}.groupBy({it -> it.setId})
        def indexList = []
        indexDict.each{ key, value ->
            indexList << value.sort{it.indexInSet}*.index*.sequence.join("-")
        }
        return indexList.join(",")
    }
    
    String getSequenceIndicesIdString() {
        def indexDict = SampleSequenceIndices.where{sample == this}.groupBy({it -> it.setId})
        def indexList = []
        indexDict.each{ key, value ->
            indexList << value.sort{it.indexInSet}*.index*.indexId.join("-")
        }
        return indexList.join(",")
    }
    
    List getProjects() {
        return ProjectSamples.where{sample == this}.collect{it.project}
    }
    
    List getTreatments() {
        return SampleTreatments.where{ sample == this }.collect{ it.treatment }
    }
    
    static constraints = {
		cellSource nullable: true
		antibody nullable: true
		target nullable: true
		spikeInCellSource nullable: true
        cellNumber nullable: true
        chromosomeAmount nullable: true
        volume nullable: true
        requestedTagNumber nullable: true
		publicationReference nullable: true, blank: true
		note nullable: true, blank: true
        sendDataTo nullable: true
        invoice nullable: true
		prtclInstSummary nullable: true
        date nullable: true
        item nullable: true
        source nullable: true
        sourceId nullable: true
        antibodyNotes nullable: true, blank: true
        assay nullable: true
        requestedGenomes nullable: true, blank: true
        recommend nullable: true
        growthMedia nullable: true
        naturalId nullable: true
    }
    
    static mapping = {
        sort date:"desc"
        dynamicUpdate: true
		note sqlType: 'longtext'
    }
}
