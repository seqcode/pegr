package pegr

class SequenceRun {
	
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
}
