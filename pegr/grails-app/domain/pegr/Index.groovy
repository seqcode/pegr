package pegr

class Index {
	String version
	int id
	String sequence 
	String oligo
	
    static constraints = {    
		version maxSize: 2
		sequence maxSize: 30 
		id unique: 'version'
		oligo maxSize: 200
	}
}
