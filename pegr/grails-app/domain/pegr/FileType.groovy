package pegr

class FileType {

	String name
	String description
	
	String toString() {
		name
	}
    static constraints = {
		name maxSize: 30, unique: true
		description maxSize: 200
    }
}
