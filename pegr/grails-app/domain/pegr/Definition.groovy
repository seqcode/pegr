package pegr

class Definition {

	String name
	String content
	
	String toString() {
		name
	}
    static constraints = {
		name unique: true
		content maxSize: 5000 
    }
}
