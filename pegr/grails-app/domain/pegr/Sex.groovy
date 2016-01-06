package pegr

class Sex {
	String name
	
	String toString() {
		name
	}
    static constraints = {
		name unique: true, size: 2..20	
    }
}
