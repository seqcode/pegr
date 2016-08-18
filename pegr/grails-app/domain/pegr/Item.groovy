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
    
    Antibody getAntibody() {
        return ItemAntibody.findByItem(this)?.antibody
    }
    
    String getSequenceIndicesString() {
        def indexDict = ItemSequenceIndices.where{item == this}.groupBy({it -> it.setId})
        def indexList = []
        indexDict.each{ key, value ->
            indexList.push(value.sort{it.indexInSet}*.index*.sequence.join("-"))
        }
        return indexList.join(",")
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
