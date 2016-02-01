package pegr

class CellSourceTreatments {

    CellSource cellSource
    CellSourceTreatment treatment
    
    static mapping = {
        version false
    }
    
    static constraints = {
        cellSource unique: 'treatment'
    }
}
