package pegr

class Item {

    String name
	String location
	String barcode
	ItemType type
	String imagePath
	String notes
    Item parent

    static constraints = {
        name nullable: true, blank: true
		location nullable: true, blank: true
		barcode unique: 'type', nullable: true, blank: true
		imagePath nullable: true, blank: true
		notes nullable: true, blank: true
        parent nullable: true
    }
}
