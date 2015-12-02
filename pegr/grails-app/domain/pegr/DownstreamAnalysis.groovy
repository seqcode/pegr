package pegr

class DownstreamAnalysis {

	String name
	
    static constraints = {
		name maxSize: 30, unique: true
    }
}
