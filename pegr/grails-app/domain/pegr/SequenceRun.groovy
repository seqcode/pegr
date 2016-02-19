package pegr

class SequenceRun {
	
	Integer runNum
	Integer lane	
	SequencingPlatform platform
	String fcId
	User user
	Date date
	String directoryName
	String note
    RunStatus status
	
    static constraints = {
		note nullable: true, blank: true
		fcId nullable: true, blank: true
		directoryName nullable: true, blank: true
        user nullable: true
        lane nullable: true, blank: true
        date nullable: true
    }

}
