package pegr

class CellSourceTreatment {
	String name
	String compound
	String quantity
	String duration
	  
	String toString() {
		String s = name
		return s
	}
	
    static constraints = {
    	name unique: true
        compound nullable: true, blank: true
		quantity nullable: true, blank: true
		duration nullable: true, blank: true
	}
}
