package pegr

class Organization {
	Organization parent
	String name
	Address address
	String note
	String website
	
    static constraints = {
		parent nullable: true
		name unique: true, maxSize: 50
		note nullable: true, maxSize: 200
		address nullable: true
		website url: true, nullable: true
    }
}


