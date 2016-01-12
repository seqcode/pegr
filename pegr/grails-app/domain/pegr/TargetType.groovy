package pegr

class TargetType {
	String name
	String description
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
		description nullable: true, blank: true
    }
}
