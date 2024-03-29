package pegr

class Target {
	
	String name
	String nTermTag
	String cTermTag
	String note
	TargetType targetType
    DictionaryStatus status

	String toString() {
		name
	}
	
    static constraints = {
		name unique: ['nTermTag', 'cTermTag'], nullable: true, blank: true
		nTermTag nullable: true, blank: true
		cTermTag nullable:true, blank: true
		note nullable: true, blank: true
        targetType nullable: true
        status nullable: true
    }
    
    static mapping = {
        sort "name"
    }
}
