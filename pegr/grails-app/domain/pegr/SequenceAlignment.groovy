package pegr

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
	Integer readDbId
	Aligner aligner
	AlignType alignType
	String alignmentParams
	Integer numberTags1
	Integer numberTags2
	Float totalType1Weight
	Float totalType2Weight
	Integer numberPairs
	Float totalPairWeight
	String bamFilePath
	String idxFilePath
	CorePipeline corePipeline
	Date dateCreated
	boolean isPreferredVersion
	
    static constraints = {
		readDbId nullable: true
		bamFilePath url: true
		idxFilePath url: true
    }
}
