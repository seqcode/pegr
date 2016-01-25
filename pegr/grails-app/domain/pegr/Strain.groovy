package pegr

class Strain {
	String name
	Genotype genotype
	Strain parent
	Lab sourceLab
	String note
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
		genotype nullable: true
        parent nullable: true
		sourceLab nullable: true
		note nullable: true, blank: true
    }
}
