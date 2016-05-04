package pegr

class Antibody {

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
    Item item
    Target defaultTarget
	
	String toString() {
		catalogNumber
	}
	
    static constraints = {
		company nullable: true
		catalogNumber nullable: true, blank: true, matches: '^[0-9A-Za-z -]+$'
		lotNumber nullable: true, blank: true
		clonal nullable: true
        abHost nullable: true
		igType nullable: true
		immunogene nullable: true, blank: true
        concentration nullable: true
		externalId nullable: true, blank: true
		inventoryId nullable: true, blank: true
		note nullable: true, blank: true
        item nullable: true
        defaultTarget nullable: true
    }
}
