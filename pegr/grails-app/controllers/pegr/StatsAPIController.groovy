package pegr
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.*

class StatsAPIController {
    def alignmentStatsService
    
    def save(StatsRegistrationCommand newStats) {
        try {
            def alignmentStats = alignmentStatsService.save(newStats)
            render "success ${newStats}"
            //    respond alignmentStats, status: 200
        } catch(Exception e) {
            log.error "Error: ${e.message}", e
            render "failed Run${newStats.run}, Sample${newStats.sample}, Genome${newStats.genome}, mapped${newStats.mappedReads}: ${e.message}"
            //respond newStats, status: 500
        }
    }    

}


class StatsRegistrationCommand {
    Long run
    Long sample
    String genome
        
    Integer totalReads
    Integer indexMismatch
    Integer adapterCount    
    Integer mappedReads
    Integer uniquelyMappedReads
    Integer dedupUniquelyMappedReads
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
    String galaxyDatasetFilepath
}

// curl -i -X GET -H "Accept: application/json" -d '{"run":191,"sample":11293,"genome":"hg19"}' localhost:8080/pegr/api/stats
// curl -i -X POST -H "Content-Type: application/json" -d '{"run":191,"sample":11293,"genome":"hg19","mappedReads":1000}' localhost:8080/pegr/api/stats