package pegr

class Definition {

	String name
	String content
	
    static constraints = {
		name unique: true
		content maxSize: 5000 
    }
}
