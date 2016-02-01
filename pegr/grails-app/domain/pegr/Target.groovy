package pegr

class Target {
	
	String name
	String nTermTag
	String cTermTag
	String note
	TargetType targetType

	String toString() {
		name
	}
	
    static constraints = {
		name unique: true, matches: '^[0-9A-Za-z -]+$'
		nTermTag nullable: true, blank: true
		cTermTag nullable:true, blank: true
		note nullable: true, blank: true
    }
}
