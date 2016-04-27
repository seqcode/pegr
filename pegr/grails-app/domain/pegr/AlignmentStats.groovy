package pegr

class AlignmentStats {
    
    Integer totalReads //indexCount
    Integer indexMismatch
    Integer adapterCount
    
    Integer mappedReads // mappedReadCount
    Integer uniquelyMappedReads // uniqueMappedReadCount
    Integer dedupUniquelyMappedReads //dedupReadCount
    Integer avgInsertSize
    Integer spikeInCount
    Float ipStrength
    
    String peakFilePath
    Integer peaks
    Integer singletons
    Float peakMedian
    Float peakMean
    Float peakMedianStd
    Float peakMeanStd
    Float medianTagSingletons
    Float peakPairNos
    Float peakPairWis
    Float genomeCoverage
    Float seqDuplicationLevel
    Integer tssProximal
    Integer tssDistal
    Integer repeatedRegions
    
    String galaxyDatasetFilepath
    
    static constraints = {
        totalReads nullable: true
        indexMismatch nullable: true
        adapterCount nullable: true
        
        mappedReads nullable: true
        uniquelyMappedReads nullable: true
        dedupUniquelyMappedReads nullable: true
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
        
        galaxyDatasetFilepath nullable: true, maxSize: 1000
    }
    
}

