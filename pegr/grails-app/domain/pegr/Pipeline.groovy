package pegr

class Pipeline {
    String workflowId
    String name
    String pipelineVersion
    String note
    String steps
    
    static constraints = {
        workflowId unique: true
        name unique: "pipelineVersion"
        note nullable: true, blank: true
        steps nullabel: true, blank: true
    }
    
    static mapping = {
        steps sqlType: 'longtext'
        workflowId defaultValue: "N/A"
        pipelineVersion defaultValue: "'0.0.0'"
    }
}
