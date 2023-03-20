package pegr

class Project {

	String name
	String description
	Date dateCreated
	Date lastUpdated
    String notes
    String links
    
	String toString() {
		name
	}
	
    List getFundings() {
        return ProjectFunding.findAllByProject(this).collect { it.funding }.toList()
    }
    
    List getSamples() {
        return ProjectSamples.where{project == this}.collect{it.sample}
    }
    
    List getCohorts() {
        return SequencingCohort.findAllByProject(this)
    }
    
    static constraints = {
		name unique: true
		description nullable: true, blank: true, maxSize: 1000
        notes nullable: true, blank: true
        links nullable: true, blank: true
    }
    
    static mapping = {
        dynamicUpdate: true
        notes sqlType: "longtext"
        links sqlType: "longtext"
    }
}
