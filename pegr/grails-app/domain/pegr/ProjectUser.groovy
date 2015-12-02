package pegr

class ProjectUser {

	enum Autherization {
		READ, WRITE
	}
	
	Project project
	User user
	Autherization autherization	
	
    static constraints = {
    }
}
