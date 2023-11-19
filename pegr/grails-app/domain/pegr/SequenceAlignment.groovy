package pegr
import groovy.json.*

class SequenceAlignment {
	SequencingExperiment sequencingExperiment
	Genome genome
    Pipeline pipeline
    String historyId
    String historyUrl
	Integer readDbId
    Aligner aligner
	AlignType alignType
	String params
	Date date
	boolean isPreferred
    
    String bamFile
    String bigwigForwardFile
    String bigwigReverseFile
    String peHistogram
    Long mappedReads
    Long uniquelyMappedReads
    Long dedupUniquelyMappedReads
    Long mappedReadsR2
    Long uniquelyMappedReadsR2
    Long dedupUniquelyMappedReadsR2
    Float seqDuplicationLevel
	Float avgInsertSize
    Float stdDevInsertSize
    Float medianInsertSize
    Float modeInsertSize
    Float genomeCoverage
    String notes
    
    static constraints = {
        historyId unique: ["sequencingExperiment", "genome", "pipeline"]
        
		readDbId nullable: true
        aligner nullable: true
        alignType nullable: true
        params nullable: true, blank: true, maxSize: 2000
        date nullable: true
        historyUrl nullable: true, blank: true
        bamFile nullable: true, blank: true, maxSize: 1000
        bigwigForwardFile nullable: true, blank: true
        bigwigReverseFile nullable: true, blank: true
        peHistogram nullable: true, blank: true, maxSize: 1000
        mappedReads nullable: true
        uniquelyMappedReads nullable: true
        dedupUniquelyMappedReads nullable: true
        mappedReadsR2 nullable: true
        uniquelyMappedReadsR2 nullable: true
        dedupUniquelyMappedReadsR2 nullable: true
        seqDuplicationLevel nullable: true
        avgInsertSize nullable: true
        stdDevInsertSize nullable: true
        medianInsertSize nullable: true
        modeInsertSize nullable: true
        genomeCoverage nullable: true
        notes nullable: true, blank: true
    }
    
    static mapping = {
        isPreferred  defaultValue: true  
     } 
}
