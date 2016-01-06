package pegr

class IgType {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name maxSize: 30; unique: true
    }
}
