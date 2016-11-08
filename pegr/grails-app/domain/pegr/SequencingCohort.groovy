package pegr
import groovy.json.*

class SequencingCohort {
    Project project
    SequenceRun run
    SummaryReport report
    String images
    String notes
    
    String toString() {
        run.id + "_" + project.name
	}
    
    List getSamples() {
        return SequencingExperiment.findAllByCohort(this).collect { it.sample }.toList()
    }
    
    List getExperiments() {
        return SequencingExperiment.findAllByCohort(this)
    }
    
    Map getImageMap() {
        def jsonSlurper = new JsonSlurper()
        def json
        try {
            json = jsonSlurper.parseText(this.images)
        } catch(Exception e) {   
        }
        return json
    }
    
    static constraints = {
        project unique: "run"
        report nullable: true
        images nullable: true
        notes nullable: true, blank: true
    }
    
    static mapping = {
        images sqlType: 'longtext'
        notes sqlType: 'longText'
    }
}