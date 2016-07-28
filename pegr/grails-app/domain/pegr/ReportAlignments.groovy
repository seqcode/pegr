package pegr

class ReportAlignments {
    SummaryReport report
    SequenceAlignment alignment
    
    static constraints = {
        report unique: 'alignment'
    }
    
    static mapping = {
        version false
    }
}