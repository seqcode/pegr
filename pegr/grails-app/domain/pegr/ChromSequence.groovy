package pegr

class ChromSequence {

	Chromosome chromosome
	int length
	String sequence
	
    static constraints = {
		sequence maxSize: 1000
    }
}
