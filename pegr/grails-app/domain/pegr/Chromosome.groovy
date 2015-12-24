package pegr

class Chromosome {

	String name
	Genome genome
	String note
	
	String toString() {
		name
	}
    static constraints = {
		name size: 2..30, unique: true
		note nullable: true, blank: true
    }
}
