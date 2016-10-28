package pegr

class SequencingCohort {
    Project project
    SequenceRun run
    SummaryReport report
    String images
    
    String toString() {
        run.id + "_" + project.name
	}
    
    List getSamples() {
        return SequencingExperiment.findAllByCohort(this).collect { it.sample }.toList()
    }
    
    List getExperiments() {
        return SequencingExperiment.findAllByCohort(this)
    }
    
    static constraints = {
        project unique: "run"
        report nullable: true
        images nullable: true
    }
    
    static mapping = {
        images sqlType: 'longtext'
    }
}