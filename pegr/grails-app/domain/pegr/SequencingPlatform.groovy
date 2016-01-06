package pegr

class SequencingPlatform {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
    	name unique: true, maxSize: 30
	}
}
