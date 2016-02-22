package pegr

class AlignType {
	String name
	String shortName
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
		shortName nullable: true, blank: true, maxSize:20, unique: true
    }
}
