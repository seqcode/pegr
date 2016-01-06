package pegr

class Address {
	String line1
	String line2
	String city
	String state
	String country
	String postalCode
	
	String toString() {
		line1
	}
	
    static constraints = {
		line1 maxSize:100
		line2 nullable: true, blank: true
		city maxSize: 30
		state maxSize: 20
		country size: 2..20
		postalCode size: 2..10
    }
}
