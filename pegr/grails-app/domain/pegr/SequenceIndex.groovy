package pegr

class SequenceIndex {
	String indexVersion
	String indexId
	String sequence 
	String oligo
	DictionaryStatus status
    
    static constraints = {    
		indexVersion maxSize: 10
		oligo nullable: true, blank: true
        status nullable: true
	}
	
	
}
