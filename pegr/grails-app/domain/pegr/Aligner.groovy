package pegr

class Aligner {
	String software
	String alignerVersion
	
    static constraints = {
		software size: 2..30, unique: 'version'
		alignerVersion nullable: true, maxSize:10
    }
}
