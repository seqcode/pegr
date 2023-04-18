package pegr

class Sex {
	String name
    String notes
	DictionaryStatus status
    
	String toString() {
		name
	}
    static constraints = {
		name unique: true
        status nullable: true
        notes nullable: true
    }
    
    static mapping = {
        sort "name"
    }
}
