package pegr

class CorePipeline {
	
	String name
	String corePipelineVersion
	BaseCalling baseCalling
	DataProcessing dataProcessing
	PeakFinding peakFinding
	DownstreamAnalysis downstreamAnalysis
	String note
	
	String toString() {
		name + corePipelineVersion
	}
	
    static constraints = {
		name maxSize: 30, unique: 'corePipelineVersion'
		corePipelineVersion nullable: true, blank: false, maxSize:10
		downstreamAnalysis nullable: true
		note nullable: true, blank: true
    }
}
