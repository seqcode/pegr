package pegr

class AlignType {
	String name
	String shortName
	
    static constraints = {
		name maxSize: 30, unique: true
		shortName nullable: true, blank: true, maxSize:20, unique: true
    }
}
