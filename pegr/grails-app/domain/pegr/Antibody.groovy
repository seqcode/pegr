package pegr

class Antibody {
	
	String name
	Company company
	String catalogNumber
	String lotNumber
	MonoPolyClonal clonal
	AbHost abHost
	IgType igType
	String immunogene
	Float concentration
	String externalId
	String inventoryId
	String note
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true, matches: '^[0-9A-Za-z -]+$'
		company nullable: true
		catalogNumber nullable: true, blank: true
		lotNumber nullable: true, blank: true, matches: '^[0-9A-Za-z -]+$'
		clonal nullable: true
        abHost nullable: true
		igType nullable: true
		immunogene nullable: true, blank: true
		externalId nullable: true, blank: true
		inventoryId nullable: true, blank: true
		note nullable: true, blank: true
    }
}
