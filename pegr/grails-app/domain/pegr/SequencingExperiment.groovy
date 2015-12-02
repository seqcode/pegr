package pegr

class SequencingExperiment {
	Sample sample
	User contact
	int sequencingReplicate
	int lane
	SequenceRun sequenceRun
	Pool pool
	double poolConcentration // ng/ul
	SeqId sequenceIndex
	int numberReads
	String fastqFilePath
	String fastQCReportPath
	String publicDbId
	String note
	
    static constraints = {
    	fastqFilePath url: true
		fastQCReportPath url: true
		note nullable: true, maxSize: 200
	}
}
