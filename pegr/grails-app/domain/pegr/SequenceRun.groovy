package pegr

class SequenceRun {
	
	int runNum
	String name
	User sequencingTechnician
	String fcId
	Date sequencingDate
	int read1Cycle
	int indexCycle
	int read2Cycle
	String directoryName
	String note
	
    static constraints = {
    }
	
	static mapping = {
		read1Cycle defaultValue: 40
		read2Cycle defaultValue: 40
		indexCycle defaultValue: 12
   }
}
