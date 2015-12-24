package pegr

class AbHost {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name maxSize: 30, unique: true
    }
}
