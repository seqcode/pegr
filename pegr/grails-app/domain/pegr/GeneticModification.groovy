package pegr

class GeneticModification {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true, matches: '^[0-9A-Za-z -]+$'
    }
}
