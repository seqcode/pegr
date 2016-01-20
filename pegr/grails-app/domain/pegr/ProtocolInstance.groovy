package pegr

class ProtocolInstance {

	Protocol protocol
	User user
	Date lastUpdated
	String note
	ProtocolStatus status
	ProtocolInstanceBag prtclInstBag	
    
	static hasMany = [items: Item]
	
    static constraints = {
		note nullable: true, blank: true
        prtclInstBag nullable: true
        user nullable: true
    }
	
}
