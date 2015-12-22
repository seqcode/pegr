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
		objectType maxsize: 30
		action maxsize: 20
		log nullable: true, blank: true
    }
}
