package pegr

class Sample {
		
	CellSource cellSource
	Antibody antibody
	Target target
	ProtocolGroup protocolGroup
	BiologicalReplicateSet biologicalReplicateSet
	TechnicalReplicateSet technicalReplicateSet
	Float concentration // in ng/ul
	Integer requestedTagNumber
	Integer chromosomeAmount // in ug
	Integer cellNumber // in M
	Integer quantityReceived //ul per aliqu used for the assay
	String publicationReference
	ProtocolInstance latestProtocolInstance
	SampleStatus status
    Date lastUpdated
	CellSource spikeInCellSource
	String note
	
	static hasMany = [projects: Project, sequenceIndices: SequenceIndex]
	static belongsTo = [Project]

    static constraints = {
		cellSource nullable: true
		antibody nullable: true
		target nullable: true
		protocolGroup nullable: true
		biologicalReplicateSet nullable: true
		technicalReplicateSet nullable: true
		spikeInCellSource nullable: true
		concentration scale: 4, nullable: true
        cellNumber nullable: true
        chromosomeAmount nullable: true
        quantityReceived nullable: true
        requestedTagNumber nullable: true
		publicationReference nullable: true, blank: true, maxSize: 30
		note nullable: true, blank: true
		latestProtocolInstance nullable: true
    }
    
    static mapping = {
        sort lastUpdated:"desc"
    }
}
