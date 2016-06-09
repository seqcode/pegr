package pegr

class ReplicateSamples {

    ReplicateSet set
    Sample sample
    
    static constraints = {
        set unique: 'sample'    
    }
    
    static mapping = {
        version false
    }
}
