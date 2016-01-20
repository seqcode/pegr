package pegr

class Assay {

    String name
    
	String toString() {
		name
	}
    
    static constraints = {
        name unique: true
    }
}
