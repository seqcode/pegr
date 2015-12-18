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
    	fastqFilePath url: true
		fastQCReportPath url: true
		note nullable: true
	}
}
