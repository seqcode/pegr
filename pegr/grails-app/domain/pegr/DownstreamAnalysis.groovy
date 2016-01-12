package pegr

class DownstreamAnalysis {

	String name
	
	String toString() {
		name
	}
    static constraints = {
		name unique: true
    }
}
