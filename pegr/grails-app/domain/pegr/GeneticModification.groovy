package pegr

class GeneticModification {

	String name
	
	static hasMany = [strains: Strain]
	static belongsTo = [Strain]
	
    static constraints = {
		name maxSize: 20, unique: true
    }
}
