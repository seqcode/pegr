package pegr

class FileType {

	String name
	String description
	String note
	
    static constraints = {
		name maxSize: 30, unique: true
		description maxSize: 200
		note maxSize: 200, nullable: true
    }
}
