package pegr

class Genome {

	String name
	Species species
    String genomeVersion
    String url
    DictionaryStatus status
	
	String toString() {
		name
	}
	
    static constraints = {
		name unqiue: true
        species nullable: true
        genomeVersion nullable: true, blank: true
        url nullable: true, blank: true
        status nullable: true
    }
}
