package pegr

class Project {

	String name
	static hasMany = [samples: Sample, projectUsers: ProjectUser]
	User owner
	String description
	String note
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name unique: true
		description nullable: true, maxSize: 200
		note nullable: true, maxSize: 200
    }
}
