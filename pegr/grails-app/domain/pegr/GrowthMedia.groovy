package pegr

class GrowthMedia {
	
	String name
	ProtocolInstance protocolInstance
	String note
	
    static constraints = {
		name maxSize: 30
		note maxSize: 200, nullable: true, blank: true
    }
}
