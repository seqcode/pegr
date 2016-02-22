package pegr

class Inventory {
	SourceType sourceType
	Date dateReceived
	User receivingUser 
	String notes
	
    static constraints = {
		sourceType nullable: true
		dateReceived nullable: true
		receivingUser nullable: true
		notes nullable: true, blank: true
    }
}
