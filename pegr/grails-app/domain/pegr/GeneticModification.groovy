package pegr

class GeneticModification {

	String name
	
	String toString() {
		name
	}
	
	static hasMany = [strains: Strain]
	static belongsTo = [Strain]
	
    static constraints = {
		name unique: true
    }
}
