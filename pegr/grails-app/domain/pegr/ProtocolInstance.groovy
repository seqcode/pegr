package pegr

class ProtocolInstance {

	Protocol protocol
	User user
	Date dateCreated
	String note
	ProtocolInstance prior
	Boolean completed
		
	static hasMany = [items: Item]
	
    static constraints = {
		note nullable: true, blank: true
		prior nullable: true
    }
	
}
