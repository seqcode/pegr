package pegr

class Project {

	String name
	String description
	String note
	Date dateCreated
	Date lastUpdated
	Funding funding

	static hasMany = [samples: Sample, projectUsers: ProjectUser]

    static constraints = {
		name unique: true
		description nullable: true, maxSize: 200
		note nullable: true, maxSize: 200
    }
}
