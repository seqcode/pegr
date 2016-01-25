package pegr

class Sample {
		
	CellSource cellSource
	Antibody antibody
	Target target
	Float concentration // in ng/ul
	Integer requestedTagNumber
	Integer chromosomeAmount // in ug
	Integer cellNumber // in M
	Integer quantityReceived //ul per aliqu used for the assay
	String publicationReference
	SampleStatus status
    Date lastUpdated
	CellSource spikeInCellSource
	String note
	Assay assay

    static constraints = {
		cellSource nullable: true
		antibody nullable: true
		target nullable: true
		spikeInCellSource nullable: true
		concentration scale: 4, nullable: true
        cellNumber nullable: true
        chromosomeAmount nullable: true
        quantityReceived nullable: true
        requestedTagNumber nullable: true
		publicationReference nullable: true, blank: true
		note nullable: true, blank: true
    }
    
    static mapping = {
        sort lastUpdated:"desc"
        dynamicUpdate: true
    }
}
