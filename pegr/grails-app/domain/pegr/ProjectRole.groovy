package pegr

class ProjectRole {

	String name
    static constraints = {
		name maxSize: 20, unique: true
    }
}
