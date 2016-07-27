package pegr

class Analysis {
    SequenceAlignment alignment
    String category
    String tool
    Pipeline pipeline
    String workflowId
    String historyId
    String stepId
    String user
    String parameters
    String statistics
    String datasets
    String note
    
    static constraints = {
        category nullable: true, blank: true
        pipeline nullable: true
        workflowId nullable: true, blank: true
        historyId nullable: true, blank: true
        parameters nullable: true, blank: true
        statistics nullable: true, blank: true
        datasets nullable: true, blank: true
        stepId nullable: true, blank: true
        user nullable: true, blank: true
    }
    
    static mapping = {
        parameters sqlType: 'text'
        statistics sqlType: 'text'
        datasets sqlType: 'text'
    }
} 
