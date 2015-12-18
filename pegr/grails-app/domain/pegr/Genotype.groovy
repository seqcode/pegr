package pegr

class Genotype {
	String name
	Species species
	String note
	
    static constraints = {
		name maxSize:30, unique: true
		note nullable: true, blank: true
    }
}
