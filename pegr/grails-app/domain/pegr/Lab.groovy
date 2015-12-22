package pegr

class Lab extends Organization{

	User pi
	User billingContact
	
    static constraints = {
		pi nullable: true
		billingContact nullable: true
    }
}
