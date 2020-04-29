package pegr
import groovy.json.*

class AnalysisWorkflowRun {
	SequencingExperiment sequencingExperiment
    String workflowCategory
    Pipeline pipeline
    String historyId
    String historyUrl
	Date date
	boolean isPreferred
    
	String params    
    Genome genome    
    String results
    String notes
    
    static constraints = {
        historyId unique: ["sequencingExperiment", "genome", "pipeline"]
        workflowCategory nullable: true
        results nullable: true
        params nullable: true, blank: true, maxSize: 2000
        date nullable: true
        historyUrl nullable: true, blank: true
        notes nullable: true, blank: true
    }
    
    static mapping = {
        isPreferred  defaultValue: true  
     } 
}
