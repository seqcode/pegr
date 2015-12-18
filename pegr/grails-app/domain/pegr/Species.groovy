package pegr

class Species {
	String name
	String genusName
	String note
	
    static constraints = {
		name unique: true, maxSize: 50
		genusName maxSize: 50 
		note nullable: true, blank: true
    }
}
