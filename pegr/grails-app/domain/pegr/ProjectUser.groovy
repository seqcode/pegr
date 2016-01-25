package pegr
import pegr.ProjectRole

class ProjectUser {
	
	Project project
	User user
	ProjectRole projectRole	
	
    static constraints = {
        project unique: "user"
    }
}
