package pegr

class Organization {
	Organization parent
	String name
	Address address
	String note
	String website
	
	String toString() {
		name
	}
	
    static constraints = {
		parent nullable: true
		name unique: true
		note nullable: true, blank: true
		address nullable: true
		website url: true, nullable: true
    }
}


