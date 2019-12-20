package pegr

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
