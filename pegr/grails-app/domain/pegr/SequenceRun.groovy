package pegr

class SequenceRun {
	
	Integer runNum
	Integer lane	
	SequencingPlatform platform
	String fcId
	User user
	Date dateCreated
	Integer read1Cycle
	Integer indexCycle
	Integer read2Cycle
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
