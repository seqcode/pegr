package pegr

class SequenceRun {
	
	int runNum
	int lane
	User sequencingTechnician
	Platform platform
	String fcId
	Date sequencingDate
	int read1Cycle
	int indexCycle
	int read2Cycle
	String directoryName
	String note
	Pool pool
	
    static constraints = {
		note nullable: true, blank: true
    }
	
	static mapping = {
		read1Cycle defaultValue: 40
		read2Cycle defaultValue: 40
		indexCycle defaultValue: 12
   }
}
