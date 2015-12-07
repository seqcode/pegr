package pegr

class Antibody {

	enum MonoPolyClonal {
		Mono, Poly
	}
	
	String name
	Company company
	Target target
	String catalogNumber
	String lotNumber
	MonoPolyClonal clonal
	AbHost abHost
	IgType igType
	String immunogene
	double concentration
	String encodeId
	String inventoryId
	String note
	
    static constraints = {
		name maxSize: 30, unique: true
		company nullable: true
		catalogNumber nullable: true
		lotNumber nullable: true
		encodeId nullable: true
		inventoryId nullable: true
		note nullable: true, maxSize: 200
    }
}
