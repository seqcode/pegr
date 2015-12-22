package pegr

class Project {

	String name
	String description
	Date dateCreated
	Date lastUpdated
	Funding funding

	static hasMany = [samples: Sample, projectUsers: ProjectUser]

    static constraints = {
		name unique: true
		description nullable: true, blank: true, maxSize: 1000
		funding nullable: true
    }
	
}
