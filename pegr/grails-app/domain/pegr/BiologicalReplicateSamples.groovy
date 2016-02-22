package pegr

class BiologicalReplicateSamples {

    BiologicalReplicateSet set
    Sample sample
    
    static constraints = {
        set unique: 'sample'    
    }
    
    static mapping = {
        version false
    }
}
