package pegr;

class RunStats {
    Float libraryVolume
    Float libraryStock
    Float libraryStdDev
    Float pctLibraryStdDev
    String cycles
    String srOrPe
    String seqCtrl
    Integer pcrCycles
    Float qubitConc
    Float qPcrConc
    Float libraryLoadedPm
    Float phiXLoaded
    Float libraryLoadedFmol
    Float clusterDensity
    Float readsPassFilter
    Float pctClustersPassFilter
    Float pctQ30
    Float qidx
    Float totalReads
    Float readsFailedDemultiplex
    Float pctReadsFailedDemultiplex
    Float pctAlignedToPhiX    
    String libraryPoolArchiveId
    Date qPcrDate
    User technician
    String notes
    
    static constraints = {
        libraryVolume nullable: true
        libraryStock nullable: true
        libraryStdDev nullable: true
        pctLibraryStdDev nullable: true
        cycles nullable: true
        srOrPe nullable: true
        seqCtrl nullable: true, blank: true
        pcrCycles nullable: true
        qubitConc nullable: true
        qPcrConc nullable: true
        libraryLoadedPm nullable: true
        phiXLoaded nullable: true
        libraryLoadedFmol nullable: true
        clusterDensity nullable: true
        readsPassFilter nullable: true
        pctClustersPassFilter nullable: true
        pctQ30 nullable: true
        qidx nullable: true
        totalReads nullable: true
        readsFailedDemultiplex nullable: true
        pctReadsFailedDemultiplex nullable: true
        pctAlignedToPhiX nullable: true
        libraryPoolArchiveId nullable: true
        qPcrDate nullable: true
        technician nullable: true
        notes nullable: true
    }
}