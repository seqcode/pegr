package pegr

class ReadType {
	String name
	String shortName
	String note
	
    static constraints = {
    	name size: 2..20, unique: true
		shortName size: 2..10, unique: true
		note nullable: true, black: true
	}
}
