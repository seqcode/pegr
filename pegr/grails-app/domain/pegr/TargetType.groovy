package pegr

class TargetType {
	String name
	String description
	String note
	
    static constraints = {
		name unique: true, size: 2..30
		description nullable: true, maxSize: 200
		note nullable: true, maxSize: 200
    }
}
