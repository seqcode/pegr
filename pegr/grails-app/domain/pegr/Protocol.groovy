package pegr

class Protocol {
	String name
    String shortName
	String protocolVersion
	String description
    User user
	Assay assay
    Boolean addAntibody
    Boolean addIndex
    DictionaryStatus status
    
	String toString() {
        String s = name
        if (protocolVersion) {
            s += " " + protocolVersion
        }
        return s
	}
    
    static hasMany = [protocolGroups: ProtocolGroup]
    static belongsTo = [ProtocolGroup]
    
    List getSharedItemTypes(){
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.SHARED}.collect{it.itemType}
    }
    
    ItemType getStartItemType() {
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.PARENT}.get(max: 1)?.itemType
    }
    
    ItemType getEndItemType() {
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.CHILD}.get(max: 1)?.itemType
    }
    
    ItemType getStartPoolType() {
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.START_POOL}.get(max: 1)?.itemType
    }
    
    ItemType getEndPoolType() {
        return ProtocolItemTypes.where{protocol == this && function == ProtocolItemFunction.END_POOL}.get(max: 1)?.itemType
    }
    
    static constraints = {
		name unique: 'protocolVersion'
        shortName nullable: true, blank: true
		protocolVersion nullable: true, blank: true, maxSize: 10
		description nullable: true, blank: true
        user nullable: true
        assay nullable: true
        addAntibody nullable: true
        addIndex nullable: true
        status nullable: true
	}

}
