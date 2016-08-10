package pegr

class Genome {

	String name
	Species species
    DictionaryStatus status
	
	String toString() {
		name
	}
	
    static constraints = {
		name unqiue: true
        species nullable: true
        status nullable: true
    }
}
