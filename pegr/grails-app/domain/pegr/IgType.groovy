package pegr

class IgType {

	String name
	
    static constraints = {
		name maxSize: 30; unique: true
    }
}
