package pegr

class SequencingExperiment {
	String seqId
	Sample sample
	SequenceRun sequenceRun
	Integer numberReads
	String fastqFilePaths
	String fastQCReportPaths
	String publicDbId
    String readPositions
	String note
	ReadType readType
	
    static constraints = {
        readPositions nullable: true, blank: true
		note nullable: true, blank: true
		publicDbId nullable: true, blank: true
	}
}
