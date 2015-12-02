package pegr

class BaseCalling {
	String name
	String version
	
    static constraints = {
		name maxSize: 30, unique: 'version'
		version maxSize: 10, nullable: true
    }
}
