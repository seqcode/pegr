package pegr

class Project {

	String name
	String description
	Date dateCreated
	Date lastUpdated
	String funding

	String toString() {
		name
	}
	
    List getSamples() {
        return ProjectSamples.where{project == this}.collect{it.sample}
    }
    
    static constraints = {
		name unique: true
		description nullable: true, blank: true, maxSize: 1000
		funding nullable: true, blank: true
    }
    
    static mapping = {
        dynamicUpdate: true
    }
}
