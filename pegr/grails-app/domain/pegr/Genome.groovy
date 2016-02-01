package pegr

class Genome {

	String name
	Species species
	String genomeBuild
	
	String toString() {
		name + "-" + genomeBuild
	}
	
    static constraints = {
		name unqiue: 'genomeBuild', matches: '^[0-9A-Za-z -]+$'
    }
}
