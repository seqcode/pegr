package pegr

class ProtocolGroup {

	String name
	Date dateCreated
    User user
    
    List getProtocols() {
        return ProtocolGroupProtocols.where {protocolGroup == this}.collect {it.protocol}
    }
    
	String toString() {
		name
	}
	
    static constraints = {
    	name unique: true
        user nullable: true
	}
    
    static mapping = {
        sort dateCreated:"desc"
    }
}
