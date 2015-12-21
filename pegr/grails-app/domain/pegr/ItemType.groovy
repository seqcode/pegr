package pegr

class ItemType {

	String name
	String objectType
	
    static constraints = {
		name maxSize: 30, unique: true
		objectType nullable: true, blank: true, maxSize: 30
    }
}
