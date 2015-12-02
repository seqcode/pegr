package pegr

class Aligner {
	String software
	String version
	
    static constraints = {
		software size: 2..30, unique: 'version'
		version nullable: true, maxSize:10
    }
}
