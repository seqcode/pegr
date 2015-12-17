package pegr

class BaseCalling {
	String name
	String baseCallingVersion
	
    static constraints = {
		name maxSize: 30, unique: 'baseCallingVersion'
		baseCallingVersion maxSize: 10, nullable: true
    }
}
