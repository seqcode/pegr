package pegr

class Analysis {
    SequenceAlignment alignment
    String category
    String tool
    String stepId
    User user
    String parameters
    String statistics
    String datasets
    Date date
    String note
    
    static constraints = {
        category nullable: true, blank: true
        parameters nullable: true, blank: true
        statistics nullable: true, blank: true
        datasets nullable: true, blank: true
        stepId nullable: true, blank: true
        user nullable: true
        date nullable: true
        note nullable: true, blank: true
    }
    
    static mapping = {
        parameters sqlType: 'longtext'
        statistics sqlType: 'longtext'
        datasets sqlType: 'longtext'
    }
} 
