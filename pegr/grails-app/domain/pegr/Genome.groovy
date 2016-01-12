package pegr

class Genome {

	String name
	Species species
	String genomeBuild
	
	String toString() {
		name + "-" + genomeBuild
	}
	
    static constraints = {
		name unqiue: 'genomeBuild'
    }
}
