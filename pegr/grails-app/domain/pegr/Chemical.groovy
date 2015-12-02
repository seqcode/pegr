package pegr

class Chemical {
	String name
	String inventoryId
	String note
	
    static constraints = {
		name size: 2..30
		inventoryId maxSize:50
		note nullable: true, maxSize: 200
    }
}
