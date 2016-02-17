package pegr

class ItemType {

	String name
	ItemTypeCategory category
	String fields
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
		fields nullable: true, blank: true
    }
}
