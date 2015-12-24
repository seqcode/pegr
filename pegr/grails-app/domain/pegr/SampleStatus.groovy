package pegr

class SampleStatus {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
    	name maxSize: 20, unique: true
	}
}
