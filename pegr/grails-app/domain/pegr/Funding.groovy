package pegr

class Funding {
	String name
	
    static constraints = {
		name size: 2..30, unique: true 
    }
}
