package pegr

class Item {

	String location
	String barcode
	ItemType type
	Long referenceId
	String imagePath
	ProtocolInstance protocolInstance
	
    static constraints = {
		location nullable: true, blank: true
		barcode unique: 'type', nullable: true, blank: true
		imagePath nullable: true, blank: true
		protocolInstance nullable: true
    }
}
