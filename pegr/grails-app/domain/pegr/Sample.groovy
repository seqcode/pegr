package pegr

class Sample {
		
	CellSource cellSource
	Antibody antibody
	Target target
	Integer requestedTagNumber
	Integer chromosomeAmount // in ug
	Integer cellNumber // in M
	Integer volume //ul per aliqu used for the assay
	String publicationReference
	SampleStatus status
    Date lastUpdated
	CellSource spikeInCellSource
	String note
	Assay assay
    User sendDataTo
    Invoice invoice

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
    }
    
    static mapping = {
        sort lastUpdated:"desc"
        dynamicUpdate: true
    }
}
