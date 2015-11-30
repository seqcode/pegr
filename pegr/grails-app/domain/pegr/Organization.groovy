package pegr

class Organization {
	Organization parent
	String name
	Address address
	String note
	
    static constraints = {
		parent nullable: true
		note nullable: true
		address nullable: true
    }
}


