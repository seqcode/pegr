package pegr

class ItemSequenceIndices {
    Item item
    SequenceIndex index
    Integer setId
    Integer indexInSet
    
    static mapping = {
        version false
    }
    
    static constraints = {
        setId nullable: true
        indexInSet nullable: true
    }
}