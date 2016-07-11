package pegr
import groovy.json.*

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
    SummaryReport summaryReport
    
    def getAnalysis() {
        def result = new DownstreamAnalysisCommand()
        def analysis = Analysis.findByAlignment(this)
        def jsonSlurper = new JsonSlurper()
        analysis.each {
            def statistics = jsonSlurper.parseText(it.statistics)
            copyProperties(statistics, result)
        }
        return result
    }
    
    def copyProperties(source, target) {
        source.each { key, value ->
            if (target.hasProperty(key) && value != null) {
                try {
                    target[key] = value
                } catch(org.codehaus.groovy.runtime.typehandling.GroovyCastException e) {
                    log.error e
                    throw new AlignmentStatsException(message: e.message)
                }                
            }
        }
    }
    
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
        summaryReport nullable: true
    }
    
    static mapping = {
        isPreferred  defaultValue: false  
     } 
}
