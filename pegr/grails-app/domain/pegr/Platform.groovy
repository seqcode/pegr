package pegr

class Platform {

	String name
	
    static constraints = {
    	name unique: true
	}
}
