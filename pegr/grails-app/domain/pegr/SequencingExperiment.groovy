package pegr

class SequencingExperiment {
	Sample sample
	SequenceRun sequenceRun
    SequencingCohort cohort
	String publicDbId
    String readPositions
	String note
	ReadType readType 
    
    String fastqFile
    String fastqcReport
    Long totalReads
    Integer indexMismatch
    Long adapterDimerCount
	
    List getAlignments() {
        return SequenceAlignment.where{sequencingExperiment == this}.list(sort: "date")
    }
    
    def getGenomes() {
        def genomes = []
        String s = sample?.requestedGenomes
        if (s) {
            genomes = s.split(",").toList()
        }
        return genomes
    }
    
    static constraints = {
        sequenceRun nullable: true
        cohort nullable: true
        readPositions nullable: true, blank: true
        readType nullable: true
		note nullable: true, blank: true
		publicDbId nullable: true, blank: true
                
        fastqFile nullable: true, blank: true, maxSize: 1000
        fastqcReport nullable: true, blank: true, maxSize: 1000
        totalReads nullable: true
        indexMismatch nullable: true
        adapterDimerCount nullable: true
	}
}
