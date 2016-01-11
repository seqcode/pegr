package pegr

class Protocol {
	String name
	String protocolVersion
	String description
	String details
	
	String toString() {
		name + " " +protocolVersion
	}
	
	static hasMany = [protocolGroups: ProtocolGroup]	
	
	static belongsTo = [ProtocolGroup]
	
    static constraints = {
		name unique: 'protocolVersion', size: 2..30
		protocolVersion nullable: true, blank: false, maxSize: 10
		description nullable: true, blank: true
		details nullable: true, blank: true
	}
	
	static mapping = {
		details sqlType: 'text'
	}
}
