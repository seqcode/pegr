package pegr

class SampleSequenceIndices {

    Sample sample
    SequenceIndex index
    Integer setId
    Integer indexInSet
    
    static constraints = {
        setId nullable: true
        indexInSet nullable: true
    }
    
    static mapping = {
        version false
    }
}
