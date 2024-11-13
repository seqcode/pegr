package pegr

class SummaryReport {
    ReportType type
    String name
    Date date
    ReportStatus status
    String note
    Pipeline pipeline
    
    static constraints = {
        note nullable: true
        pipeline nullable: true
    }
    
    SequencingCohort getCohort() {
        return SequencingCohort.findByReport(this)
    }
    
}