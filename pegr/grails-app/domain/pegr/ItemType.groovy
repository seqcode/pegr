package pegr

class ItemType {

	String name
	String objectType
	String fields
	
	String toString() {
		name
	}
	
    static constraints = {
		name maxSize: 30, unique: true
		objectType nullable: true, blank: true, maxSize: 30
		fields nullable: true, blank: true
    }
}