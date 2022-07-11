package pegr

class Histology {

    Histology parent
	String name
    DictionaryStatus status
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
        parent nullable: true
        status nullable: true
    }
    
    static mapping = {
        sort "name"
    }
}
