package pegr

class SampleAudit {

    Boolean contaminated
    Boolean warning
    String notes
    
    static constraints = {
        contaminated nullable: true
        warning nullable: true
        notes nullable: true, blank: true
    }
}