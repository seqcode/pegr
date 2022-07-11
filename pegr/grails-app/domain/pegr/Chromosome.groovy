package pegr

class Chromosome {

	String name
	Genome genome
	String note
	
	String toString() {
		name
	}
    static constraints = {
		name unique: true
		note nullable: true, blank: true
    }
    
    static mapping = {
        sort "name"
    }
}
