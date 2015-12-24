package pegr

class ProtocolGroup {

	String name
	List protocols
	
	String toString() {
		name
	}
	
	static hasMany = [protocols: Protocol]
	
    static constraints = {
    	name maxSize: 30, unique: true
	}
}
