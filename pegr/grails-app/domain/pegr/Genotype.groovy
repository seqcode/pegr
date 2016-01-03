package pegr

class Genotype {
	String name
	Species species
	String note
	
	String toString() {
		return name
	}
	
    static constraints = {
		name maxSize:30, unique: true
		note nullable: true, blank: true
    }
}
