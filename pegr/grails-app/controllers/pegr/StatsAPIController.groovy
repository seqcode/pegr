package pegr
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.*

class StatsAPIController {
    def alignmentStatsService
    
    // to test the API: curl -i -X POST -H "Content-Type: application/json" -d '{"run":191,"sample":11293,"genome":"hg19","ipStrength":1.12}' localhost:8080/pegr/api/stats?apiKey=
    def save(StatsRegistrationCommand newStats, String apiKey) {
        def trueKey = Chores.findByName('GalaxyAPIKey').value
        
        if (apiKey == trueKey) {
            try {
                def alignmentStats = alignmentStatsService.save(newStats)
                render  status: 200
            } catch(Exception e) {
                log.error "Error: ${e.message}", e
                render status: 500
            }
        } else {
            render status: 401
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

