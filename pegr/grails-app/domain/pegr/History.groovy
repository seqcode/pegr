package pegr

class History {

	User user
	Date timestamp
	Project project
	int objectId
	String objectType
	String action
	String log

    static constraints = {
		project nullable: true
		objectType maxsize: 20
		action maxsize: 10
		log maxsize: 200, nullable: true
    }
}
