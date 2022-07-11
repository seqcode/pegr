package pegr

class Species {
	String name
	String genusName
	String note
	DictionaryStatus status
    
	String toString() {
        "${genusName} ${name}"
	}
    
    static constraints = {
		name unique: "genusName"
		note nullable: true, blank: true
        status nullable: true
    }
    
    static mapping = {
        sort "genusName"
    }
}
