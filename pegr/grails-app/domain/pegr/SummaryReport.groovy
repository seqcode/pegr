package pegr

class SummaryReport {
    ReportType type
    String name
    Date date
    User user
    ReportStatus status
    String note
    
    static constraints = {
        note nullable: true
    }
    
    SequencingCohort getCohort() {
        return SequencingCohort.findByReport(this)
    }
    
}