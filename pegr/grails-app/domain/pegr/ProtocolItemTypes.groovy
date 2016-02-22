package pegr

class ProtocolItemTypes {

    Protocol protocol
    ItemType itemType
    ProtocolItemFunction function
    
    static constraints = {
        protocol unique: 'itemType'
    }
        
    static mapping = {
        version false
    }
}
