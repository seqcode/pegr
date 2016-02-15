package pegr

class ProtocolInstanceBag {

    String name
    ProtocolGroup protocolGroup
    Date startTime
	Date endTime
	ProtocolStatus status
    
    static hasMany = [tracedSamples: Sample]
    
    static belongsTo = [Sample]
    
    String toString() {
        name
    }
    
    static constraints = {
        protocolGroup nullable: true
        name nullable: true, blank: true
        startTime nullable: true
        endTime nullable: true
    }
}
