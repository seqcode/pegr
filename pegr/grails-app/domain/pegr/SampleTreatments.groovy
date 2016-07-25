package pegr

class SampleTreatments {
    Sample sample
    CellSourceTreatment treatment
    
    static constraints = {
        sample unique: 'treatment'
    }
    
    static mapping = {
        version false
    }
}