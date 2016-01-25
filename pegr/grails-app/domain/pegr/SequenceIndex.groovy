package pegr

class SequenceIndex {
	String indexVersion
	Integer indexId
	String sequence 
	String oligo
	
    static constraints = {    
		indexVersion maxSize: 10
		indexId unique: 'indexVersion'
		oligo nullable: true, blank: true
	}
	
	
}
