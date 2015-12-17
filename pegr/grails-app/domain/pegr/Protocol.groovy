package pegr

class Protocol {
	String name
	String protocolVersion
	Assay assay
	String description
	String url
	Protocol parent
	
	
    static constraints = {
		name unique: 'protocolVersion', size: 2..30
		protocolVersion nullable: true, maxSize: 10
		description maxSize: 1000
    	url url:true, nullable: true
		parent nullable: true
	}
}
