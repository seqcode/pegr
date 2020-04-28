package pegr

class ProcessAnalysisJob {
    static triggers = {}

    static concurrent = false
    
    def analysisService
    
    void execute(context) {        
        def id = context.mergedJobDataMap.get('id')
        try {
            analysisService.processAnalysis(id)
        } catch(Exception e) {
            log.error e
        }
    }
}
