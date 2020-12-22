package pegr

class Organization {
	Organization parent
	String name
	Address address
	String note
	String website
    DictionaryStatus status
	
	String toString() {
		name
	}
    
    static mapping = {
        address cascade: 'all-delete-orphan'
    }
	
    static constraints = {
		parent nullable: true
		name unique: true
		note nullable: true, blank: true
		address nullable: true
		website url: true, nullable: true
        status nullable: true
    }
}


