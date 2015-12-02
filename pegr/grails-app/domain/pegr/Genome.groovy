package pegr

class Genome {

	String name
	Species species
	String genomeBuild
	
    static constraints = {
		name size: 2..30, unqiue: true
		genomeBuild size: 2..30
    }
}
