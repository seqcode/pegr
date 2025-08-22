package pegr

class SequenceIndex {
	String indexId
	String sequence 
	String oligo
	DictionaryStatus status
    
    static constraints = {    
		oligo nullable: true, blank: true
        status nullable: true
	}
	
	
}
