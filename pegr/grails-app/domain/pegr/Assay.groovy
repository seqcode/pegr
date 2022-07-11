package pegr

class Assay {

    String name
    String description
    
	String toString() {
		name
	}
    
    static constraints = {
        name unique: true, matches: '^[0-9A-Za-z -]+$'
        description blank: true, nullable: true
    }
    
    static mapping = {
        sort "name"
    }
}
