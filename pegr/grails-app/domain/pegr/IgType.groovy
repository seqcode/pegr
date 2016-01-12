package pegr

class IgType {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
    }
}
