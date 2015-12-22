package pegr

class Tissue {
	String name
	
    static constraints = {
		name unique: true, size: 2..30
    }
}
