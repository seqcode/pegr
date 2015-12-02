package pegr

class DataProcessing {

	String name
	
    static constraints = {
		name maxSize: 30, unique: true
    }
}
