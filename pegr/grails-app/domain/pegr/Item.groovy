package pegr

class Item {

	String location
	String barcode
	ItemType type
	Long referenceId
	String imagePath
	ProtocolInstance protocolInstance
	String notes
	
    static constraints = {
		referenceId nullable: true
		location nullable: true, blank: true
		barcode unique: 'type', nullable: true, blank: true
		imagePath nullable: true, blank: true
		protocolInstance nullable: true
		notes nullable: true, blank: true
    }
}
