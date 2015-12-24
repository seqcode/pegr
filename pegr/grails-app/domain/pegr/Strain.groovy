package pegr

class Strain {
	String name
	Genotype genotype
	Strain parent
	String tag
	Lab sourceLab
	String note
	
	String toString() {
		name
	}
	
	static hasMany = [geneticModifications: GeneticModification]
	
    static constraints = {
		name unique: true
		parent nullable: true
		sourceLab nullable: true
		tag nullable: true, blank: true
		note nullable: true, blank: true
    }
}
