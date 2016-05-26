package pegr

class SampleSequenceIndices {

    Sample sample
    SequenceIndex index
    Integer setId
    
    static constraints = {
        setId nullable: true
    }
    
    static mapping = {
        version false
    }
}
