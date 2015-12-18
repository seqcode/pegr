package pegr

class Protocol {
	String name
	String protocolVersion
	String description
	Protocol parent	
	
    static constraints = {
		name unique: 'protocolVersion', size: 2..30
		protocolVersion nullable: true, maxSize: 10
		description maxSize: 1000, nullable: true, blank: true
		parent nullable: true
	}
}
