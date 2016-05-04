package pegr
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.*
import groovy.json.*

class StatsAPIController {
    def alignmentStatsService
    
    // to test the API: curl -i -X POST -H "Content-Type: application/json" -d '{"run":2,"sample":2,"genome":"sacCer3_cegr","ipStrength":1.12}' localhost:8080/pegr/api/stats?apiKey=
    def save(StatsRegistrationCommand newStats, String apiKey) {
        def trueKey = Chores.findByName('GalaxyAPIKey').value

        if (apiKey == trueKey) {
            try {
                def results = alignmentStatsService.save(newStats)
                def message = ""
                if (results.size()) {
                    message += "Properties ${results} have been updated for Run ${newStats.run}, Sample ${newStats.sample} and Genome ${newStats.genome}."
                } else {
                    message += "No property has been updated for Run ${newStats.run}, Sample ${newStats.sample} and Genome ${newStats.genome}."
                }
                def response = new ResponseMessage(response_code: 200, message: message)
                render text: response as JSON, contentType: "text/json", status: 200 
            } catch(AlignmentStatsException e) {
                def response = new ResponseMessage(response_code: 500, message: "Error: ${e.message}")
                render text: response as JSON, contentType: "text/json", status: 500 
            } catch(Exception e) {
                log.error "Error: ${e.message}", e
                def response = new ResponseMessage(response_code: 500, message: "Error!")
                render text: response as JSON, contentType: "text/json", status: 500 
            }
        } else {
            def response = new ResponseMessage(response_code: 401, message: "Not authorized!")
            render text: response as JSON, contentType: "text/json", status: 401 
        }
    }    
}

class ResponseMessage {
    String response_code
    String message
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

    String bamFile
    String fastqFile
    String genetrackFile
    String cwpairFile
    String memeFile
    String fimoFile
    String fourColorFig
    String heatmapFig
    String compositeFig
}

