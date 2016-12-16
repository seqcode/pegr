package pegr

class ProtocolInstance {
    def utilityService
    
	Protocol protocol
	User user
    Date startTime
	Date endTime
	String note
	ProtocolStatus status
	ProtocolInstanceBag bag	
    Integer bagIdx
    String images
	
    def getImageMap() {
        return utilityService.parseJson(this.images)
    }
    
    static constraints = {
        protocol nullable: true
		note nullable: true, blank: true
        bag nullable: true
        user nullable: true
        startTime nullable: true
        endTime nullable: true
        images nullable: true
    }
    
    static mapping = {
        bagIdx defaultValue: 0
    }
	
}
