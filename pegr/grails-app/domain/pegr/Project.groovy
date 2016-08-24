package pegr

class Project {

	String name
	String description
	Date dateCreated
	Date lastUpdated

	String toString() {
		name
	}
	
    List getSamples() {
        return ProjectSamples.where{project == this}.collect{it.sample}
    }
    
    static constraints = {
		name unique: true
		description nullable: true, blank: true, maxSize: 1000
    }
    
    static mapping = {
        dynamicUpdate: true
    }
}
