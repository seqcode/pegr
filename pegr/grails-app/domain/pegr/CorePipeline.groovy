package pegr

class CorePipeline {
	
	String name
	String version
	BaseCalling baseCalling
	DataProcessing dataProcessing
	PeakFinding peakFinding
	DownstreamAnalysis downstreamAnalysis
	String note
	
    static constraints = {
		name maxSize: 30, unique: 'version'
		version nullable: true, maxSize:10
		downstreamAnalysis nullable: true
		note nullable: true, maxSize: 200
    }
}
