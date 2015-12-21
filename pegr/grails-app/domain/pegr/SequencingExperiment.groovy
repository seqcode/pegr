package pegr

class SequencingExperiment {
	String seqId
	Sample sample
	SequenceRun sequenceRun
	int numberReads
	String fastqFilePaths
	String fastQCReportPaths
	String publicDbId
	String note
	ReadType readType
	
    static constraints = {
		note nullable: true, blank: true
	}
}
