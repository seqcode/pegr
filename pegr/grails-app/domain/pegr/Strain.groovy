package pegr

class Strain {
	String name
	Genotype genotype
	Strain parent
	Lab sourceLab
    Sex sex
	String note
	
	String toString() {
		name
	}
	
	static hasMany = [geneticModifications: GeneticModification]
	
    static constraints = {
		name unique: true
		genotype nullable: true
        parent nullable: true
        sex nullable: true
		sourceLab nullable: true
		note nullable: true, blank: true
    }
}
