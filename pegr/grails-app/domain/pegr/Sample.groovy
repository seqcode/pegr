package pegr

class Sample {
	enum SampleQC {
		SUCCESS, PENDING, FAILED
	}
		
	CellSource cellSource
	Antibody antibody
	Target target
	ProtocolInstance protocolInstance
	BiologicalReplicateSet biologicalReplicateSet
	TechnicalReplicateSet technicalReplicateSet
	double concentration // in ng/ul
	int requestedTagNumber
	int chromosomeAmount // in ug
	int cellNumber // in M
	int quantityReceived //ul per aliqu used for the assay
	String publicationReference
	SampleQC sampleQC
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
