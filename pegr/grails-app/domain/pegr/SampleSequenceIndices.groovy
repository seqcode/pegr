package pegr

class SampleSequenceIndices {

    Sample sample
    SequenceIndex index
    Integer setId
    
    static constraints = {
        sample unique: 'index'
        setId nullable: true
    }
    
    static mapping = {
        version false
    }
}
