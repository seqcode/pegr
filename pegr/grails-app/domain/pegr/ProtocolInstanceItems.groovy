package pegr

class ProtocolInstanceItems {

    ProtocolInstance protocolInstance
    Item item
    
    static constraints = {
        protocolInstance unique: 'item'
    }
    
    static mapping = {
        version false
    }
}
