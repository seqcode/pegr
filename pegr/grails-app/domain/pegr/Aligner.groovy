package pegr

class Aligner {
	String software
	String alignerVersion
	
    static constraints = {
		software size: 2..30, unique: 'alignerVersion'
		alignerVersion nullable: true, blank: false, maxSize:10
    }
}
