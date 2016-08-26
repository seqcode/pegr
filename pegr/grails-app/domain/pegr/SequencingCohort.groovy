package pegr

class SequencingCohort {
    Project project
    SequenceRun run
    SummaryReport report
    
    String toString() {
        run.id + "_" + project.name
	}
    
    List getSamples() {
        return SequencingExperiment.findAllByCohort(this).collect { it.sample }.toList()
    }
    
    static constraints = {
        project unique: "run"
        report nullable: true
    }
    
}