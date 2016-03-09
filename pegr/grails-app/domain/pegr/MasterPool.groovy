package pegr;

class MasterPool {
    Float libraryVolume
    Float libraryStock
    Float stdDev
    Float pctStdDev
    Date qPCRDate
    User technician
    String instrument
    Integer cycles
    String srOrPe
    String seqCtrl
    Integer PcrCycles
    Float quibitConc
    Float qPcrConc
    Float libraryLoaded
    Float phiXLoaded
    Float libraryLoadedFmol
    Float clusterNum
    Float readPf
    Float pctPf
    Float pctQ30
    Float Qidx
    Float totalReads
    Float unmatchedIndices
    Float pctUnmatched
    Float pctAlignedToPhiX    
    String Notes
    
    static constraints = {
        libraryVolume nullable: true
        libraryStock nullable: true
        stdDev nullable: true
        pctStdDev nullable: true
        qPCRDate nullable: true
        technician nullable: true
        instrument nullable: true
        cycles nullable: true
        srOrPe nullable: true
        seqCtrl nullable: true
        pcrCycles nullable: true
        quibitConc nullable: true
        qPcrConc nullable: true
        libraryLoaded nullable: true
        phiXLoaded nullable: true
        libraryLoadedFmol nullable: true
        clusterNum nullable: true
        readPf nullable: true
        pctPf nullable: true
        pctQ30 nullable: true
        qidx nullable: true
        totalReads nullable: true
        unmatchedIndices nullable: true
        pctUnmatched nullable: true
        pctAlignedToPhiX nullable: true
        Notes nullable: true
    }
}