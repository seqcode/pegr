package pegr

class SequencingExperiment {
	Sample sample
	SequenceRun sequenceRun	
	String fastqFilePath
	String publicDbId
    String readPositions
	String note
	ReadType readType
	
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
        fastqFilePath nullable: true, blank: true, maxSize: 500
        readPositions nullable: true, blank: true
        readType nullable: true
		note nullable: true, blank: true
		publicDbId nullable: true, blank: true
	}
}
