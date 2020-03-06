package pegr

/**
 * Join table between ProtocolGroup and Protocol. 
 * A protocolGroup comprises an ordered list of protocols.
 */
class ProtocolGroupProtocols {

	Protocol protocol
	ProtocolGroup protocolGroup
    Integer protocolsIdx
	
    static constraints = {
    	protocol unique: ['protocolGroup', 'protocolsIdx']
	}

    static mapping = {
        sort protocolsIdx: "asc"
        version false
    }
}
