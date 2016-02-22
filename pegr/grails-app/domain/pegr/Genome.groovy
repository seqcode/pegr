package pegr

class Genome {

	String name
	Species species
    DictionaryStatus status
	
	String toString() {
		name
	}
	
    static constraints = {
		name unqiue: true, matches: '^[0-9A-Za-z -]+$'
        species nullable: true
        status nullable: true
    }
}
