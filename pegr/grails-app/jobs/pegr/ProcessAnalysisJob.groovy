package pegr

class ProcessAnalysisJob {
    static triggers = {}

    def concurrent = false
    
    def alignmentStatsService
    
    def execute(context) {
        def id = context.mergedJobDataMap.get('id')
        processAnalysis(id)
    }
}
