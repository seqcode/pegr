package pegr

class History {

	User user
	Date dateCreated
	Project project
	Integer objectId
	String objectType
	String action
	String notes

    static constraints = {
		project nullable: true
		notes nullable: true, blank: true
    }
}
