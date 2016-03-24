package pegr

class SequencingExperiment {
	String seqId
	Sample sample
	SequenceRun sequenceRun
	Integer numberReads // same as index_count
	String fastqFilePath
	String publicDbId
    String readPositions
	String note
	ReadType readType
    Integer indexMismatch
    Integer adapterCount
	
    List getAlignments() {
        return SequenceAlignment.where{sequencingExperiment == this}.list()
    }
    
    List getGenomes() {
        return SequenceAlignment.where{sequencingExperiment == this}.collect{it.genome}
    }
    
    String getGenomesString() {
        def genomes = SequenceAlignment.where{sequencingExperiment == this}.collect{it.genome.name}
        return genomes.join(', ')
    }
    
    static constraints = {
        seqId nullable: true, blank: true
        sequenceRun nullable: true
        numberReads nullable: true
        fastqFilePath nullable: true, blank: true, maxSize: 500
        readPositions nullable: true, blank: true
        readType nullable: true
		note nullable: true, blank: true
		publicDbId nullable: true, blank: true
        indexMismatch nullable: true
        adapterCount nullable: true
	}
}
