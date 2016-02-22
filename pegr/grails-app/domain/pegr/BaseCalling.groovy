package pegr

class BaseCalling {
	String name
	String baseCallingVersion
	
	String toString() {
		name + baseCallingVersion
	}
    static constraints = {
		name unique: 'baseCallingVersion'
		baseCallingVersion maxSize: 10, nullable: true, blank: false
    }
}
