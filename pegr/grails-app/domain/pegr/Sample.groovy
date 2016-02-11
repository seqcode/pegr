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
    Date date
	CellSource spikeInCellSource
	String note
    User sendDataTo
    Invoice invoice
	ProtocolInstanceSummary prtclInstSummary

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
    }
    
    static mapping = {
        sort date:"desc"
        dynamicUpdate: true
		note sqlType: 'text'
    }
}
