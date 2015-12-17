package pegr

class Item {

	ProtocolItem protocolItem
	ProtocolInstance protocolInstance
	String barcode
	String location
	String note
	
    static constraints = {
		barcode nullable: true, blank: true
		note nullable: true, blank: true
    }
}
