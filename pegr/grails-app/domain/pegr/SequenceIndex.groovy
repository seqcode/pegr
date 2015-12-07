package pegr

class SequenceIndex {
	String indexVersion
	int indexId
	String sequence 
	String oligo
	
    static constraints = {    
		indexVersion maxSize: 2
		sequence maxSize: 30 
		indexId unique: 'indexVersion'
		oligo maxSize: 200
	}
	
	
}
