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
    
    String toString() {
        def name = "${run.id}_${project.name}"
        return name
    }
}