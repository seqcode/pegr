package pegr

class Pipeline {
    String workflowId
    String name
    String pipelineVersion
    String note
    String steps
    String workflowUrl
    String reportModules
    String sampleModules
    String printModules
    boolean isDefault
    
    static constraints = {
        name unique: "pipelineVersion"
        pipelineVersion nullable: false
        workflowId unique: true
        workflowUrl nullable: true, blank: true
        steps nullabel: false, blank: false, widget: 'textarea'
        reportModules nullable: true, blank: true, widget: 'textarea'
        sampleModules nullable: true, blank: true, widget: 'textarea'
        printModules nullable: true, blank: true, widget: 'textarea'
        note nullable: true, blank: true
    }
    
    static mapping = {
        steps sqlType: 'longtext'
        workflowId defaultValue: "N/A"
        pipelineVersion defaultValue: "'0.0.0'"
        sort "name"
    }
    
    String toString() {
        "${name}_${pipelineVersion}"
	}
}
