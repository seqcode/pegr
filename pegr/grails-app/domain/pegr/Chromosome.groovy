package pegr

class Chromosome {

	String name
	Genome genome
	String note
	
    static constraints = {
		name size: 2..30
		note nullable: true, maxSize: 200
    }
}
