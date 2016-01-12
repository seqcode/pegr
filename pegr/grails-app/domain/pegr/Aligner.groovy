package pegr

class Aligner {
	String software
	String alignerVersion
	
	String toString() {
		software + " " + alignerVersion
	}
	
    static constraints = {
		software unique: 'alignerVersion'
		alignerVersion nullable: true, blank: false, maxSize:10
    }
}
