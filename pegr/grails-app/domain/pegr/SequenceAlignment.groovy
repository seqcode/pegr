package pegr

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
	Integer readDbId
	Aligner aligner
	AlignType alignType
	String params
	CorePipeline corePipeline
	Date date
	boolean isPreferred
    AlignmentStats alignmentStats
	
    static constraints = {
		readDbId nullable: true
        aligner nullable: true
        alignType nullable: true
        params nullable: true, blank: true, maxSize: 2000
        corePipeline nullable: true
        date nullable: true
        alignmentStats nullable: true
    }
    
    static mapping = {
        isPreferred  defaultValue: false  
     } 
}
