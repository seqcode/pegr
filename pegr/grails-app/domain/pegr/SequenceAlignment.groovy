package pegr

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
	String alignId
	int readDbId
	Aligner aligner
	int numberTags1
	int numberTags2
	int totalType1Weight
	int totalType2Weight
	int numberPairs
	int totalPair
	String bamFilePath
	String idxFilePath
	CorePipeline corePipelineDescription
	Date alignmentDate
	boolean isPreferredVersion
	
    static constraints = {
		bamFilePath url: true
		idxFilePath url: true
    }
}
