package pegr

class PoolSamples {
    Item pool
    Sample sample
    
    static constraints = {
        sample unique: 'pool'
    }
    
    static mapping = {
        version false
    }
}