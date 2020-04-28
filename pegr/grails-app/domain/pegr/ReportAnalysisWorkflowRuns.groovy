package pegr

class ReportAnalysisWorkflowRuns {
    SummaryReport report
    AnalysisWorkflowRun analysisWorkflowRun
    
    static constraints = {
        report unique: 'analysisWorkflowRun'
    }
    
    static mapping = {
        version false
    }
}