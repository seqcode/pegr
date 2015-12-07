package pegr

class GrowthMedia {
	
	String name
	Protocol protocol
	String note
	
    static constraints = {
		name maxSize: 30
		note maxSize: 200, nullable: true, blank: true
    }
}
