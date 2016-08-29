package pegr

class Genome {

	String name
	Species species
    String url
    DictionaryStatus status
	
	String toString() {
		name
	}
	
    static constraints = {
		name unqiue: true
        species nullable: true
        url nullable: true, blank: true
        status nullable: true
    }
}
