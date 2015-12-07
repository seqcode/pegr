package pegr

class AbHost {

	String name
	
    static constraints = {
		name maxSize: 30, unique: true
    }
}
