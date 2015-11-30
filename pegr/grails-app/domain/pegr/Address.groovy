package pegr

class Address {
	String line1
	String line2
	String state
	String country
	String postalCode
	
    static constraints = {
		line2 nullable: true
    }
}
