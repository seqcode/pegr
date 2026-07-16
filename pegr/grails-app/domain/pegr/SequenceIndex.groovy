package pegr

class SequenceIndex {
	String indexId
	String sequence 
	String oligo
	DictionaryStatus status
    
    static constraints = {
		indexId unique: true
		oligo nullable: true, blank: true
        status nullable: true
	}
	
	
}
