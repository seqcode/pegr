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
    Long totalReadsR2
    Integer indexMismatch
    Long adapterDimerCount
    Long adapterDimerCountR2
	
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
        totalReadsR2 nullable: true
        indexMismatch nullable: true
        adapterDimerCount nullable: true
        adapterDimerCountR2 nullable: true
	}
}
