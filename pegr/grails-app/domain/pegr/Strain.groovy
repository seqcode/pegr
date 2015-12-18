package pegr

class Strain {
	String name
	Genotype genotype
	Strain parent
	String tag
	Lab sourceLab
	String note
	
	static hasMany = [geneticModifications: GeneticModification]
	
    static constraints = {
		note nullable: true, blank: true
		tag nullable: true, blank: true
    }
}
