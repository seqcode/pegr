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
		line2 nullable: true, blank: true
		city maxSize: 50
		state maxSize: 50
		country maxSize: 50
		postalCode maxSize: 10
    }
}
