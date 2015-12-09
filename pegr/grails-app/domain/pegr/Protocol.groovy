package pegr

class Protocol {
	String name
	Assay experimentType
	String description
	String url
	
    static constraints = {
		name unique: true, size: 2..30
		description maxSize: 200
    	url url:true, nullable: true
	}
}
