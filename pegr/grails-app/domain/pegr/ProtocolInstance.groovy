package pegr

class ProtocolInstance {

	Protocol protocol
	String biologicalMaterial
	String note
	Date dateTime
	
    static constraints = {
		biologicalMaterial nullable: true, maxSize: 30
		note nullable: true
    }
}
