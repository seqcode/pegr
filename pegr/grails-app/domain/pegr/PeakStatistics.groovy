package pegr

class PeakStatistics {
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

