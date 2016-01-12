package pegr

class Species {
	String name
	String genusName
	String note
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
		note nullable: true, blank: true
    }
}
