package pegr

class Pipeline {
    User user
    String workflowId
    String name
    String pipelineVersion
    String note
    String steps
    
    static constraints = {
        workflowId unique: "user"
        name unique: ["user", "pipelineVersion"]
        note nullable: true, blank: true
        steps nullabel: true, blank: true
    }
    
    static mapping = {
        workflowId defaultValue: "N/A"
        pipelineVersion defaultValue: "0.0.0"
    }
}