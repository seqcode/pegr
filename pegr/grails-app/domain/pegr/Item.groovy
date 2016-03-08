package pegr

class Item {

    String name
	String location
	String barcode
	ItemType type
	User user
	String notes
    Item parent

    static constraints = {
        name nullable: true, blank: true
		location nullable: true, blank: true
		barcode unique: 'type', nullable: true, blank: true
		user nullable: true
		notes nullable: true, blank: true
        parent nullable: true
    }
}
