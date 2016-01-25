package pegr

class Tissue {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
    }
}
