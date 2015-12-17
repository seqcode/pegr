package pegr

class ProtocolItem {

	Protocol protocol
	String name
	String field1
	String field2
	ProtocolItem parent
	
    static constraints = {
		field2 nullable: true, blank: true
		parent nullable: true
    }
}
