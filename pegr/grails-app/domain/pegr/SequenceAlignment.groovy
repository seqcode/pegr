package pegr

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
	Integer readDbId
	Aligner aligner
	AlignType alignType
	String params
	String bamFilePath
	CorePipeline corePipeline
	Date date
	boolean isPreferred
    Integer mappedReadCount
    Integer uniqueMappedReadCount
    Integer dedupReadCount
    Integer avgInsertSize
    PeakStatistics peakStatistics
	
    static constraints = {
		readDbId nullable: true
        aligner nullable: true
        alignType nullable: true
        params nullable: true, blank: true, maxSize: 2000
		bamFilePath nullable: true, blank: true, maxSize: 1000
        corePipeline nullable: true
        date nullable: true
        mappedReadCount nullable: true
        uniqueMappedReadCount nullable: true
        dedupReadCount nullable: true
        avgInsertSize nullable: true
        peakStatistics nullable: true
    }
    
    static mapping = {
        isPreferred  defaultValue: false  
     } 
}
