package pegr

class SequenceIndex {
	String indexVersion
	Integer indexId
	String sequence 
	String oligo
	
	static hasMany = [samples: Sample]
	
	static belongsTo = [Sample]
	
    static constraints = {    
		indexVersion maxSize: 10
		sequence maxSize: 30 
		indexId unique: 'indexVersion'
		oligo nullable: true, blank: true
	}
	
	
}
