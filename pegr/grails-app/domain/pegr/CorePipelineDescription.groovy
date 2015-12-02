package pegr

class CorePipelineDescription {
	
	String name
	String version
	BaseCalling baseCalling
	DataProcessing dataProcessing
	PeakFinding peakFinding
	DownstreamAnalysis downstreamAnalysis
	String note
	
    static constraints = {
    }
}
