package pegr

class History {

	User user
	Date timestamp
	Project project
	int object_id
	String object_type
	String action
	String log

    static constraints = {
		project nullable: true
		object_type maxsize: 20
		action maxsize: 10
		log maxsize: 200, nullable: true
    }
}
