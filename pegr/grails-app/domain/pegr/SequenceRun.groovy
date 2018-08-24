package pegr

class SequenceRun {
	
	Integer runNum //This is what we show the user. run.id will be used only to access the views and manages the relationships and connections in the backend.
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
	String runNumAlias //for second/old numbering system. Not really used currently except to keep the old data.
	
    List getExperiments() {
        return SequencingExperiment.where{sequenceRun == this}.list()
    }
    
    List getCohorts() {
        return SequencingCohort.findAllByRun(this)
    }
    
    static constraints = {
		runNum nullable: false, unique: true //new constraints added to start using runNum proprely. It's never been used correctly or at all (08/09/2018 by axa677)
		note nullable: true, blank: true
		fcId nullable: true, blank: true
		directoryName nullable: true, blank: true
        user nullable: true
        lane nullable: true //is going to be derpecated after updating the queue form (08/09/2018 by axa677)
        date nullable: true
        runStats nullable: true
        poolItem nullable: true
		runNumAlias nullable: true //new field added on 08/09/2018 by axa677
    }

}
