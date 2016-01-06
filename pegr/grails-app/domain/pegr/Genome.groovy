package pegr

class Genome {

	String name
	Species species
	String genomeBuild
	
	String toString() {
		name + "-" + genomeBuild
	}
	
    static constraints = {
		name size: 2..30, unqiue: 'genomeBuild'
		genomeBuild size: 2..30
    }
}
