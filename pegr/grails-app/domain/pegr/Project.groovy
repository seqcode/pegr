package pegr

class Project {

	String name
	static hasMany = [samples: Sample, projectUsers: ProjectUser]
	User owner
	String description
	String note
	
    static constraints = {
    }
}
