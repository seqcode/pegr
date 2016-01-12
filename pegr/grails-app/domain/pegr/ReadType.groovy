package pegr

class ReadType {
	String name
	String shortName
	String note
	
	String toString() {
		name
	}
	
    static constraints = {
    	name unique: true
		shortName size: 2..10, unique: true
		note nullable: true, blank: true
	}
}
