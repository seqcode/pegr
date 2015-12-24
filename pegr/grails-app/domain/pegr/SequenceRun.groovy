package pegr

class SequenceRun {
	
	int runNum
	int lane	
	SequencingPlatform platform
	String fcId
	User user
	Date dateTime
	int read1Cycle
	int indexCycle
	int read2Cycle
	String directoryName
	String note
	Pool pool
	
    static constraints = {
		note nullable: true, blank: true
		fcId nullable: true, blank: true
		directoryName nullable: true, blank: true
    }
	
	static mapping = {
		read1Cycle defaultValue: 40
		read2Cycle defaultValue: 40
		indexCycle defaultValue: 12
   }
}
