package pegr

class TreatmentsInCellSource {

    CellSource cellSource
    CellSourceTreatment treatment
    
    static mapping = {
        version false
    }
    
    static constraints = {
        cellSource unique: 'treatment'
    }
}
