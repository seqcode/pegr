package pegr

class ProtocolInstance {

	Protocol protocol
	User user
	Date dateCreated
	String note
	ProtocolStatus status
	ProtocolInstanceBag prtclInstBag	
    
	static hasMany = [items: Item]
	
    static constraints = {
		note nullable: true, blank: true
        prtclInstBag nullable: true
    }
	
}
