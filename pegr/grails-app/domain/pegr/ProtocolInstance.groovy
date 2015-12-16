package pegr

class ProtocolInstance {

	Protocol protocol
	String biologicalMaterial
	String note

	
    static constraints = {
		biologicalMaterial nullable: true, maxSize: 30
		note nullable: true, maxSize: 200
    }
}
