package pegr

class Sample {
	enum SampleQC {
		SUCCESS, PENDING, FAILED
	}
		
	User user
	Date prepareDate
	CellSource cellSource
	Antibody antibody
	Target target
	ProtocolInstance protocolInstance
	int biologicalReplicate
	int technicalReplicate
	double libraryConcentration // in ng/ul
	int requestedTagNumber
	int chromosomeAmount // in ug
	int cellNumber // in M
	int quantityReceived //ul per aliqu used for the assay
	int pcrCycle
	Resin resin
	String publicationReference
	SampleQC sampleQC
	CellSource spikeInSample
	String note
	
	static hasMany = [projects: Project]
	static belongsTo = [Project]
	
    static constraints = {
		publicationReference nullable: true
		spikeInSample nullable: true
		libraryConcentration scale: 4
		publicationReference nullable: true, maxSize: 30
		spikeInSample nullable: true
		note nullable: true, maxSize: 200
    }
}
