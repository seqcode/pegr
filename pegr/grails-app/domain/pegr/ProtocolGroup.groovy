package pegr

class ProtocolGroup {

	String name
	List protocols
	Date dateCreated
    User user
    
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
