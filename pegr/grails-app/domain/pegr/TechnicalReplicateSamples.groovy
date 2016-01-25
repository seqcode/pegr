package pegr

class TechnicalReplicateSamples {
    TechnicalReplicateSet set
    Sample sample
    
    static constraints = {
        set unique: 'sample'    
    }
    
    static mapping = {
        version false
    }
}
