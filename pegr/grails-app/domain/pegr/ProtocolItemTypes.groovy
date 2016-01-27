package pegr

class ProtocolItemTypes {

    Protocol protocol
    ItemType itemType
    
    static constraints = {
        protocol unique: 'itemType'
    }
        
    static mapping = {
        version false
    }
}
