package pegr

class ProjectFunding {
    Project project
    Funding funding
    
    static constraints = {
        project unique: "funding"
    }
    
    static mapping = {
		id composite: ['project', 'funding']
		version false
	}
}