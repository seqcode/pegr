package pegr

class ControlSample {
    Sample sample
    Sample controlSample
    
    static constraints = {
        sample unique: 'controlSample'
    }
    
    static mapping = {
        version false
    }
}