package pegr

class SummaryReport {
    Project project
    SequenceRun run
    
    static constraints = {
        project unique: "run"
    }
    
    static mapping = {
        version false
    }
}