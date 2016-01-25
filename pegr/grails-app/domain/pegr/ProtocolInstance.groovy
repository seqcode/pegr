package pegr

class ProtocolInstance {

	Protocol protocol
	User user
    Date startTime
	Date endTime
	String note
	ProtocolStatus status
	ProtocolInstanceBag bag	
    Integer bagIdx
	
    static constraints = {
		note nullable: true, blank: true
        bag nullable: true
        user nullable: true
        startTime nullable: true
        endTime nullable: true
        bagIdx nullable: true
    }
	
}
