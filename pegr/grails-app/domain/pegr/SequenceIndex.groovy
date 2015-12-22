package pegr

class SequenceIndex {
	String indexVersion
	int indexId
	String sequence 
	String oligo
	
    static constraints = {    
		indexVersion maxSize: 10
		sequence maxSize: 30 
		indexId unique: 'indexVersion'
		oligo nullable: true, blank: true
	}
	
	
}
