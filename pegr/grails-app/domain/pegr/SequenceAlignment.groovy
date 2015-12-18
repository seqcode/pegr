package pegr

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
	int readDbId
	Aligner aligner
	AlignType alignType
	String alignmentParams
	int numberTags1
	int numberTags2
	double totalType1Weight
	double totalType2Weight
	int numberPairs
	double totalPairWeight
	String bamFilePath
	String idxFilePath
	CorePipeline corePipeline
	Date alignmentDate
	boolean isPreferredVersion
	
    static constraints = {
		bamFilePath url: true
		idxFilePath url: true
    }
}
