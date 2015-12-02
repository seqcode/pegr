package pegr

class Organization {
	Organization parent
	String name
	Address address
	String note
	String website
	
    static constraints = {
		parent nullable: true
		note nullable: true
		address nullable: true
		website url: true, nullable: true
    }
}


