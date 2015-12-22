package pegr

class Target {
	
	String name
	String nTermTag
	String cTermTag
	String note
	TargetType targetType

    static constraints = {
		name unique: true, size: 2..30
		nTermTag nullable: true, blank: true
		cTermTag nullable:true, blank: true
		note nullable: true, blank: true
    }
}
