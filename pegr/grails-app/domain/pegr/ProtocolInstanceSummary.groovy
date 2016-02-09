package pegr

class ProtocolInstanceSummary {
	Date startTime
	Date endTime
	User user
	Protocol protocol
	String note
	
    static constraints = {
		startTime nullable: true
		endTime nullable: true
		user nullable: true
		protocol nullable: true
		note nullable: true, blank: true
    }
}
