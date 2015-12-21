package pegr

class Sample {
		
	CellSource cellSource
	Antibody antibody
	Target target
	ProtocolGroup protocolGroup
	BiologicalReplicateSet biologicalReplicateSet
	TechnicalReplicateSet technicalReplicateSet
	double concentration // in ng/ul
	int requestedTagNumber
	int chromosomeAmount // in ug
	int cellNumber // in M
	int quantityReceived //ul per aliqu used for the assay
	String publicationReference
	SampleStatus status
	CellSource spikeInCellSource
	String note
	
	static hasMany = [projects: Project]
	static belongsTo = [Project]

    static constraints = {
		publicationReference nullable: true
		spikeInCellSource nullable: true
		concentration scale: 4
		publicationReference nullable: true, maxSize: 30
		note nullable: true, blank: true
    }
}
