package pegr

class SequenceRun {
	Integer lane	
	SequencingPlatform platform
	String fcId
	User user
	Date date
	String directoryName
	String note
    RunStatus status
    RunStats runStats
    Item poolItem
    String runName
	
    List getExperiments() {
        return SequencingExperiment.where{sequenceRun == this}.list()
    }
    
    List getCohorts() {
        return SequencingCohort.findAllByRun(this)
    }
    
    static constraints = {
		note nullable: true, blank: true
		fcId nullable: true, blank: true
		directoryName nullable: true, blank: true
        user nullable: true
        lane nullable: true
        date nullable: true
        runStats nullable: true
        poolItem nullable: true
        runName unique: true, nullable: true
    }

}
