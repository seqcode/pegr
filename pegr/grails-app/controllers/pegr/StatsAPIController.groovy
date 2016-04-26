package pegr

class StatsAPIController {
    def alignmentStatsService
    def save(StatsRegistrationCommand newStats) {
        try {
            alignmentStatsService.save(newStats)
            respond status: 200
        } catch(Exception e) {
            respond status: 500
        }
    }    
}


class StatsRegistrationCommand {
    Long run
    Long sample
    String genome
    String mappedReadCount
    String uniqueMappedReadCount
    String dedupReadCount
}

// curl -i -X POST -H "Content-Type: application/json" -d '{"run":200}' localhost:8080/pegr/statsAPI