package pegr

class ProtocolInstance {

	Protocol protocol
	User user
	Date dateTime
	String note
	ProtocolInstance prior
		
	static hasMany = [itmes: Item]
	
    static constraints = {
		note nullable: true, blank: true
		prior nullable: true
    }
}
