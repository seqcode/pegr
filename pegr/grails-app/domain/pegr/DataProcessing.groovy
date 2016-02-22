package pegr

class DataProcessing {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
    }
}
