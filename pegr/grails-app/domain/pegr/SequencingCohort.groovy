package pegr

class SequencingCohort {
    Project project
    SequenceRun run
    SummaryReport report
    
    static constraints = {
        project unique: "run"
        report nullable: true
    }
    
}