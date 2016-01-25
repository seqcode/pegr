package pegr

class Item {

    String name
	String location
	String barcode
	ItemType type
	Long referenceId
	String imagePath
	String notes
    Item parent
    List bags
    
    static hasMany = [bags: ProtocolInstanceBag]

    static constraints = {
        name nullable: true, blank: true
		referenceId nullable: true
		location nullable: true, blank: true
		barcode unique: 'type', nullable: true, blank: true
		imagePath nullable: true, blank: true
		notes nullable: true, blank: true
        parent nullable: true
    }
}
