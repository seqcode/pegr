package pegr

class ProtocolItem {

	Protocol protocol
	String type
	String name
	String quantity
	String note
	ProtocolItem parent
	boolean isBarcoded
	
    static constraints = {
		quantity nullable: true, blank: true
		note nullable: true, blank: true
		parent nullable: true
    }
}
