package pegr

class Strain {
	String name
    Species species
	String genotype
    Strain parent
    String geneticModification
	Lab sourceLab
	String note
	DictionaryStatus status
    
    static mappedBy = [parent: 'none', backgroundStrain: 'none']
    
	String toString() {
        name
	}
    
    static constraints = {
		name matches: '^[0-9A-Za-z -.]+$', nullable: true, blank: true
        species nullable: true
        genotype nullable: true, blank: true
        parent nullable: true
        geneticModification matches: '^[0-9A-Za-z -]+$', nullable: true, blank: true
		sourceLab nullable: true
		note nullable: true, blank: true
        status nullable: true
    }
}
