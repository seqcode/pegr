package pegr

class ProtocolInstanceBag {

    PrtclInstBagType type
    String name
    List protocolInstances
	Date lastUpdated
	ProtocolStatus status
    static hasMany = [protocolInstances: ProtocolInstance]
    
    static constraints = {
    }
}
