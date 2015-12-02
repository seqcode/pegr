package pegr

class ProtocolInstance {

	Protocol protocol
	String biologicalMaterial
	String note
	Reagent reagent
	
    static constraints = {
		biologicalMaterial nullable: true, maxSize: 30
		note nullable: true, maxSize: 200
    }
}
