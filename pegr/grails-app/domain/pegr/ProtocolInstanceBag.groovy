package pegr

class ProtocolInstanceBag {

    List protocolInstances
    
    static hasMany = [protocolInstances: ProtocolInstance]
    
    static constraints = {
    }
}
