package pegr

class ProtocolGroup {

	String name
	Date dateCreated
    User user
    List protocols
    
	String toString() {
		name
	}
    
    static hasMany = [protocols: Protocol]
	
    static constraints = {
    	name unique: true
        user nullable: true
	}
    
    static mapping = {
        sort dateCreated:"desc"
    }
}
