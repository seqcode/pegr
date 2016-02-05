package pegr

class SequenceRun {
	
	Integer runNum
	Integer lane	
	SequencingPlatform platform
	String fcId
	User user
	Date dateCreated
	String directoryName
	String note
	String pool
	
    static constraints = {
		note nullable: true, blank: true
		fcId nullable: true, blank: true
		directoryName nullable: true, blank: true
        user nullable: true
        pool nullable: true, blank: true
        lane nullable: true, blank: true
    }

}
