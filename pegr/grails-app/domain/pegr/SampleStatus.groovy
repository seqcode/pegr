package pegr

class SampleStatus {

	String name
	
    static constraints = {
    	name maxSize: 20, unique: true
	}
}
