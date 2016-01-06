package pegr

class TargetType {
	String name
	String description
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true, size: 2..30
		description nullable: true, blank: true
    }
}
