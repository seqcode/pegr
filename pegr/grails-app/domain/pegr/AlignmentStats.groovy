package pegr

class AlignmentStats {
    
    Integer indexCount
    Integer indexMismatch
    Integer adapterCount
    
    Integer mappedReadCount
    Integer uniqueMappedReadCount
    Integer dedupReadCount
    Integer avgInsertSize
    Integer spikeInCount
    Float ipStrength
    
    String peakFilePath
    Integer peaks
    Integer singletons
    Integer peakMedian
    Integer peakMean
    Integer peakMedianStd
    Integer peakMeanStd
    Integer medianTagSingletons
    Integer peakPairNos
    Integer peakPairWis
    Float genomeCoverage
    Float seqDuplicationLevel
    Integer tssProximal
    Integer tssDistal
    Integer repeatedRegions
    
    static constraints = {
        indexCount nullable: true
        indexMismatch nullable: true
        adapterCount nullable: true
        
        mappedReadCount nullable: true
        uniqueMappedReadCount nullable: true
        dedupReadCount nullable: true
        avgInsertSize nullable: true
        spikeInCount nullable: true
        ipStrength nullable: true
        
        peakFilePath nullable: true
        peaks nullable: true
        singletons nullable: true
        peakMedian nullable: true
        peakMean nullable: true
        peakMedianStd nullable: true
        peakMeanStd nullable: true
        medianTagSingletons nullable: true
        peakPairNos nullable: true
        peakPairWis nullable: true
        genomeCoverage nullable: true
        seqDuplicationLevel nullable: true
        tssProximal nullable: true
        tssDistal nullable: true
        repeatedRegions nullable: true
    }
    
}

