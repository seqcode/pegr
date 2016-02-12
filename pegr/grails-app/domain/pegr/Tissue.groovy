package pegr

class Tissue {

	String name
    DictionaryStatus status
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
        status nullable: true
    }
}
