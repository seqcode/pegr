package pegr

class SequencingExperiment {
	Sample sample
	User contact
	int sequencingReplicateId
	int lane
	SequenceRun sequenceRun
	Pool pool
	int numberReads
	String fastqFilePath
	String fastQCReportPath
	String publicDbId
	Platform platform
	SequenceIndex sequenceIndex
	String note
	ReadType readType
	
    static constraints = {
    	fastqFilePath url: true
		fastQCReportPath url: true
		note nullable: true
	}
}
