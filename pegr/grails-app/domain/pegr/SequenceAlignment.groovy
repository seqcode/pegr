package pegr

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
	Integer readDbId
	Aligner aligner
	AlignType alignType
	String params
	String filePaths
	CorePipeline corePipeline
	Date date
	boolean isPreferred
	
    static constraints = {
		readDbId nullable: true
        aligner nullable: true
        alignType nullable: true
        params nullable: true, blank: true, maxSize: 2000
		filePaths nullable: true, blank: true, maxSize: 1000
        corePipeline nullable: true
        date nullable: true
    }
    
    static mapping = {
        isPreferred  defaultValue: false  
     } 
}
