package pegr

class ReferenceFeature {
    String filename
    Genome genome
    String summary
    String url
    
    String toString() {
		filename
	}
	
    static constraints = {
		filename unique: true
        genome nullable: true
        summary nullable: true, blank: true
        url nullable: true, blank: true
    }
}