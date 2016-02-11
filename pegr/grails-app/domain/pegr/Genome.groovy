package pegr

class Genome {

	String name
	Species species
	
	String toString() {
		name
	}
	
    static constraints = {
		name unqiue: true, matches: '^[0-9A-Za-z -]+$'
        species nullable: true
    }
}
