package pegr

class CellSourceTreatment {
	String name
	String note
    DictionaryStatus status
	  
	String toString() {
		return name
	}
	
    static constraints = {
    	name unique: true
        note nullable: true, blank: true
        status nullable: true
	}
    
    static mapping = {
		note sqlType: 'text'
    }
}
