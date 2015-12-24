package pegr

class ProjectRole {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name maxSize: 20, unique: true
    }
}
