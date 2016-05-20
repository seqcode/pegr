package pegr

class SequencingExperiment {
	Sample sample
	SequenceRun sequenceRun	
	String publicDbId
    String readPositions
	String note
	ReadType readType 
    
    String fastqFile
    String fastqcReport
    Long totalReads
    Integer indexMismatch
    Long adapterCount
	
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
        sequenceRun nullable: true        
        readPositions nullable: true, blank: true
        readType nullable: true
		note nullable: true, blank: true
		publicDbId nullable: true, blank: true
                
        fastqFile nullable: true, blank: true, maxSize: 1000
        fastqcReport nullable: true, blank: true, maxSize: 1000
        totalReads nullable: true
        indexMismatch nullable: true
        adapterCount nullable: true
	}
}
