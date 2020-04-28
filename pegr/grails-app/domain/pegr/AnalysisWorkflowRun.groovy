package pegr
import groovy.json.*

class AnalysisWorkflowRun {
	SequencingExperiment sequencingExperiment
    String workflowCategory
    Pipeline pipeline
    String historyId
    String historyUrl
	Date date
	boolean isPreferred
    
	String params    
    Genome genome    
    Aligner aligner
	AlignType alignType
    
    String results
    Integer readDbId
    String bamFile
    String peHistogram
    Long mappedReads
    Long uniquelyMappedReads
    Long dedupUniquelyMappedReads
    Float seqDuplicationLevel
	Float avgInsertSize
    Float stdDevInsertSize
    Float genomeCoverage
    String notes
    
    static constraints = {
        historyId unique: ["sequencingExperiment", "genome", "pipeline"]
        workflowCategory nullable: true
        results nullable: true
		readDbId nullable: true
        aligner nullable: true
        alignType nullable: true
        params nullable: true, blank: true, maxSize: 2000
        date nullable: true
        historyUrl nullable: true, blank: true
        bamFile nullable: true, blank: true, maxSize: 1000
        peHistogram nullable: true, blank: true, maxSize: 1000
        mappedReads nullable: true
        uniquelyMappedReads nullable: true
        dedupUniquelyMappedReads nullable: true
        seqDuplicationLevel nullable: true
        avgInsertSize nullable: true
        stdDevInsertSize nullable: true
        genomeCoverage nullable: true
        notes nullable: true, blank: true
    }
    
    static mapping = {
        isPreferred  defaultValue: true  
     } 
}
