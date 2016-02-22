package pegr

class SampleSequenceIndices {

    Sample sample
    SequenceIndex index
    
    static constraints = {
        sample unique: 'index'
    }
    
    static mapping = {
        version false
    }
}
