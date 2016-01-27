package pegr

class Protocol {
	String name
	String protocolVersion
	String description
	String details
    User user
	Assay assay
    
	String toString() {
        String s = name
        if (protocolVersion) {
            s += " " +protocolVersion
        }
        return s
	}
    
    static hasMany = [protocolGroups: ProtocolGroup]
    static belongsTo = [ProtocolGroup]
	
    static constraints = {
		name unique: 'protocolVersion'
		protocolVersion nullable: true, blank: false, maxSize: 10
		description nullable: true, blank: true
		details nullable: true, blank: true
        user nullable: true
        assay nullable: true
	}
	
	static mapping = {
		details sqlType: 'text'
	}
}
