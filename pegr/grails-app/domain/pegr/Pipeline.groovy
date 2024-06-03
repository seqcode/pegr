package pegr

class Pipeline {
    String workflowId
    String name
    String pipelineVersion
    String note
    String steps
    String workflowUrl
    
    static constraints = {
        name unique: "pipelineVersion"
        pipelineVersion nullable: false
        workflowId unique: true
        workflowUrl nullable: true, blank: true
        steps nullabel: true, blank: true
        note nullable: true, blank: true
    }
    
    static mapping = {
        steps sqlType: 'longtext'
        workflowId defaultValue: "N/A"
        pipelineVersion defaultValue: "'0.0.0'"
        sort "name"
    }
    
    String toString() {
        "${name}-${pipelineVersion}"
	}
}
