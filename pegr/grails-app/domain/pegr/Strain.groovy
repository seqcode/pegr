package pegr

class Strain {
	String name
    Species species
	String genotype
    Strain backgroundStrain
    Strain parent
    String geneticModification
	Lab sourceLab
	String note
	
    static mappedBy = [parent: 'none', backgroundStrain: 'none']
    
	String toString() {
        if (name != null && name != ""){
            name
        } else {
            "${backgroundStrain?.name}-${geneticModification}"
        }
	}
    
    static constraints = {
		name matches: '^[0-9A-Za-z -]+$', nullable: true, blank: true
        species nullable: true
        genotype nullable: true, blank: true
        backgroundStrain nullable: true
        parent nullable: true
        geneticModification matches: '^[0-9A-Za-z -]+$', nullable: true, blank: true
		sourceLab nullable: true
		note nullable: true, blank: true
    }
}
