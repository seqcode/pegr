package pegr

class SequencingExperiment {
	String seqId
	Sample sample
	SequenceRun sequenceRun
	Integer numberReads
	String filePaths
	String publicDbId
    String readPositions
	String note
	ReadType readType
	
    static constraints = {
        seqId nullable: true, blank: true
        sequenceRun nullable: true
        numberReads nullable: true
        filePaths nullable: true, blank: true, maxSize: 500
        readPositions nullable: true, blank: true
        readType nullable: true
		note nullable: true, blank: true
		publicDbId nullable: true, blank: true
	}
}
