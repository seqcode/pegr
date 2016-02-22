package pegr

class IgType {

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
