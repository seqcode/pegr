package pegr

class Protocol {
	String name
	ExperimentType experimentType
	String description
	String url
	
    static constraints = {
    	url url:true
	}
}
