package pegr

class ProtocolInstanceBag {

    String name
    ProtocolGroup protocolGroup
    Date startTime
	Date endTime
	ProtocolStatus status
    ProtocolInstanceBag superBag
    
    static hasMany = [tracedItems: Item]
    
    static belongsTo = [Item]
    
    String toString() {
        name
    }
    
    static constraints = {
        protocolGroup nullable: true
        name nullable: true, blank: true
        startTime nullable: true
        endTime nullable: true
        superBag nullable: true
    }
}
