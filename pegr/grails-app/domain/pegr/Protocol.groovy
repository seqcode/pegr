package pegr

class Protocol {
	String name
	String protocolVersion
	String description
	String details
    User user
	Assay assay
    
	String toString() {
        String s = name
        if (protocolVersion) {
            s += " " +protocolVersion
        }
        return s
	}
    
    static hasMany = [protocolGroups: ProtocolGroup]
    static belongsTo = [ProtocolGroup]
	
    List getSharedItemTypes(){
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.SHARED}.collect{it.itemType}
    }
    
    List getIndividualItemTypes(){
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.INDIVIDUAL}.collect{it.itemType}
    }
    
    ItemType getStartItemType() {
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.PARENT}.get(max: 1)?.itemType
    }
    
    ItemType getEndItemType() {
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.CHILD}.get(max: 1)?.itemType
    }
    
    static constraints = {
		name unique: 'protocolVersion'
		protocolVersion nullable: true, blank: true, maxSize: 10
		description nullable: true, blank: true
		details nullable: true, blank: true
        user nullable: true
        assay nullable: true
	}
	
	static mapping = {
		details sqlType: 'text'
	}
}
