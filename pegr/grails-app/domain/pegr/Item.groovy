package pegr
import groovy.json.*

class Item {
    def utilityService
    
    String name
	String location
	String barcode
	ItemType type
	User user
	String notes
    Item parent
    String customizedFields
    Project project
    String status
    Date lastUpdated
    boolean active = true

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
            indexList << value.sort{it.indexInSet}*.index*.sequence.join("-")
        }
        return indexList.join(",")
    }
    
    String getSequenceIndicesIdString() {
        def indexDict = ItemSequenceIndices.where{item == this}.groupBy({it -> it.setId})
        def indexList = []
        indexDict.each{ key, value ->
            indexList << value.sort{it.indexInSet}*.index*.indexId.join("-")
        }
        return indexList.join(",")
    }
    
    Map getFieldMap() {        
        def jsonSlurper = new JsonSlurper()
        def json

        try {
            json = jsonSlurper.parseText(this.customizedFields)
        } catch(Exception e) {   
        }
        return json
    }
    
    static constraints = {
		location nullable: true, blank: true
		barcode unique: true
		user nullable: true
		notes nullable: true, blank: true
        parent nullable: true
        customizedFields nullable: true
        project nullable: true
        status nullable: false
        lastUpdated nullable: true
    }
    
    static mapping = {
        customizedFields sqlType: 'longtext'
        status defaultValue: "GOOD"
    }
}
