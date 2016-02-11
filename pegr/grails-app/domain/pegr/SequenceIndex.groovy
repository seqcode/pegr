package pegr

class SequenceIndex {
	String indexVersion
	Integer indexId
	String sequence 
	String oligo
	
    static constraints = {    
		indexVersion maxSize: 10
		oligo nullable: true, blank: true
	}
	
	
}
