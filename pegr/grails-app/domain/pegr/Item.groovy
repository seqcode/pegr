package pegr

class Item {

	String location
	String barcode
	ItemType type
	Long referenceId
	String imagePath
	String notes
	
    static hasMany = [protocolInstances: ProtocolInstance]
    static belongsTo = [ProtocolInstance]
    
    static constraints = {
		referenceId nullable: true
		location nullable: true, blank: true
		barcode unique: 'type', nullable: true, blank: true
		imagePath nullable: true, blank: true
		notes nullable: true, blank: true
    }
}
