package pegr
import groovy.json.*

class SequencingCohort {
    String name
    Project project
    SequenceRun run
    SummaryReport report
    String images
    String notes
    
    String toString() {
        this.name
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
        name nullable: true
        report nullable: true
        images nullable: true
        notes nullable: true, blank: true
    }
    
    static mapping = {
        images sqlType: 'longtext'
        notes sqlType: 'longText'
    }
}