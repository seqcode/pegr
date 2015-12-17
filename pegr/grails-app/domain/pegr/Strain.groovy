package pegr

class Strain {
	String name
	Genotype genotype
	Strain parent
	Lab sourceLab
	
	static hasMany = [geneticModifications: GeneticModification]
	
    static constraints = {
    }
}
