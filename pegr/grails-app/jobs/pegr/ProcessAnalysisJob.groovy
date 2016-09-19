package pegr

class ProcessAnalysisJob {
    static triggers = {}

    def concurrent = false
    
    def alignmentStatsService
    
    def execute(context) {        
        def id = context.mergedJobDataMap.get('id')
        try {
            alignmentStatsService.processAnalysis(id)
        } catch(Exception e) {
            log.error e
        }
    }
}
