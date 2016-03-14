package pegr

class Item {

    String name
	String location
	String barcode
	ItemType type
	User user
	String notes
    Item parent

    List getSamplesInPool() {
        return PoolSamples.where{pool == this}.collect{it.sample}
    }
    
    List getRelatedInstances() {
        return ProtocolInstanceItems.where{item == this}.collect{it.protocolInstance}        
    }
    
    static constraints = {
        name nullable: true, blank: true
		location nullable: true, blank: true
		barcode unique: 'type', nullable: true, blank: true
		user nullable: true
		notes nullable: true, blank: true
        parent nullable: true
    }
}
