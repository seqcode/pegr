package pegr

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
	Integer readDbId
    Aligner aligner
	AlignType alignType
	String params
	Date date
	boolean isPreferred
    
    String bamFile
    String peHistogram
    Long mappedReads
    Long uniquelyMappedReads
    Long dedupUniquelyMappedReads
    Float seqDuplicationLevel
	Float avgInsertSize
    Float stdDevInsertSize
    Float genomeCoverage
    
    static constraints = {
		readDbId nullable: true
        aligner nullable: true
        alignType nullable: true
        params nullable: true, blank: true, maxSize: 2000
        date nullable: true
        
        bamFile nullable: true, blank: true, maxSize: 1000
        peHistogram nullable: true, blank: true, maxSize: 1000
        mappedReads nullable: true
        uniquelyMappedReads nullable: true
        dedupUniquelyMappedReads nullable: true
        seqDuplicationLevel nullable: true
        avgInsertSize nullable: true
        stdDevInsertSize nullable: true
        genomeCoverage nullable: true
    }
    
    static mapping = {
        isPreferred  defaultValue: false  
     } 
}
