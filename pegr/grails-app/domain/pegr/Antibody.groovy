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
	
	String toString() {
		String s = name
		if(inventoryId)
			s += inventoryId
		return s
	}
	
    static constraints = {
		name maxSize: 30, unique: true
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
