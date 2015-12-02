package pegr

class ExperimentType {

	String name
	String code
	String note
	
    static constraints = {
		name maxSize: 30, unique: true
		code maxSize:10, unique: true
		note maxSize: 200, nullable: true
    }
}
