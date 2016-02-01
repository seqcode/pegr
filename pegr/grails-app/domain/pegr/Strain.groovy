package pegr

class Strain {
	String name
    Species species
	Genotype genotype
	Strain parent
	Lab sourceLab
	String note
	
	String toString() {
		name
	}
	
    List getGeneticModifications(){
        return StrainGeneticModifications.where{strain == this}.collect{it.geneticModification}
    }
    
    static constraints = {
		name unique: true, matches: '^[0-9A-Za-z -]+$'
		genotype nullable: true
        parent nullable: true
		sourceLab nullable: true
		note nullable: true, blank: true
    }
}
