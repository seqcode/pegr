package pegr

class Inventory {
	SourceType sourceType
	Date dateReceived
	User receivingUser 
    String location
	String notes
	
    static constraints = {
		sourceType nullable: true
		dateReceived nullable: true
		receivingUser nullable: true
        location nullable: true, blank: true
		notes nullable: true, blank: true
    }
}
