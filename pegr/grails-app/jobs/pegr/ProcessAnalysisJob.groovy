package pegr

class ProcessAnalysisJob {
    static triggers = {}

    static concurrent = false
    
    def alignmentStatsService
    
    void execute(context) {        
        def id = context.mergedJobDataMap.get('id')
        try {
            alignmentStatsService.processAnalysis(id)
        } catch(Exception e) {
            log.error e
        }
    }
}
