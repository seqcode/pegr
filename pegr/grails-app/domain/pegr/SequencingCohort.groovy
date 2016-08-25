package pegr

class SequencingCohort {
    Project project
    SequenceRun run
    SummaryReport report
    
    List getSamples() {
        return SequencingExperiment.findAllByCohort(this).collect { it.sample }.toList()
    }
    
    static constraints = {
        project unique: "run"
        report nullable: true
    }
    
}