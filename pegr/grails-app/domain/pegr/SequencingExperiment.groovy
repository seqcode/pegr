package pegr

class SequencingExperiment {
	String seqId
	Sample sample
	SequenceRun sequenceRun
	Integer numberReads
	String fastqFilePaths
	String fastQCReportPaths
	String publicDbId
	String note
	ReadType readType
	
    static constraints = {
		note nullable: true, blank: true
		publicDbId nullable: true, blank: true
	}
}
