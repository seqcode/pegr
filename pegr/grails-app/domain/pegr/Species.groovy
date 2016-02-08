package pegr

class Species {
	String name
	String genusName
	String note
	
	String toString() {
        "${genusName} ${name}"
	}
	
    static constraints = {
		name unique: true, matches: '^[0-9A-Za-z -.]+$'
        genusName matches: '^[0-9A-Za-z -]+$'
		note nullable: true, blank: true
    }
}
