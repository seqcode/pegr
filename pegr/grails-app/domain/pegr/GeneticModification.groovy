package pegr

class GeneticModification {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
    }
}
