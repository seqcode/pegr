package pegr

class ProtocolItemTypes {

    Protocol protocol
    ItemType itemType
    ProtocolItemFunction function
    
    static constraints = {
        protocol unique: ['itemType', 'function']
    }
        
    static mapping = {
        version false
    }
}
