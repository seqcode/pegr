package pegr

class AbHost {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
    }
}
