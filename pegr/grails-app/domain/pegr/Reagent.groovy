package pegr

class Reagent {

	String name
	String inventoryId
	Chemical chemical
	
    static constraints = {
    	name size: 2..20
		inventoryId maxSize: 30
		
	}
}
