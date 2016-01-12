package pegr

class ItemType {

	String name
	String objectType
	String fields
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
		objectType nullable: true, blank: true
		fields nullable: true, blank: true
    }
}
