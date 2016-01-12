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
	Float concentration
	String encodeId
	String inventoryId
	String note
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
		company nullable: true
		catalogNumber nullable: true, blank: true
		lotNumber nullable: true, blank: true
		clonal nullable: true
		igType nullable: true
		immunogene nullable: true, blank: true
		encodeId nullable: true, blank: true
		inventoryId nullable: true, blank: true
		note nullable: true, blank: true
    }
}
