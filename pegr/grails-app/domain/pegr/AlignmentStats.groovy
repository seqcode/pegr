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
    
    String bamFile
    String fastqFile
    String genetrackFile
    String cwpairFile
    String memeFile
    String fimoFile
    String fourColorFig
    String heatmapFig
    String compositeFig
    
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
        
        bamFile nullable: true, blank: true, maxSize: 1000
        fastqFile nullable: true, blank: true, maxSize: 1000
        genetrackFile nullable: true, blank: true, maxSize: 1000
        cwpairFile nullable: true, blank: true, maxSize: 1000
        memeFile nullable: true, blank: true, maxSize: 1000
        fimoFile nullable: true, blank: true, maxSize: 1000
        fourColorFig nullable: true, blank: true, maxSize: 1000
        heatmapFig nullable: true, blank: true, maxSize: 1000
        compositeFig nullable: true, blank: true, maxSize: 1000

    }
    
}

