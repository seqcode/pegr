package pegr

class ProtocolInstance {

	Protocol protocol
	User user
	Date dateTime
	String note
	
    static constraints = {
		note nullable: true
    }
}
