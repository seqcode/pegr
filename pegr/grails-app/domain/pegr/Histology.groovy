package pegr

class Histology {

    Histology parent
	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		name unique: true
        parent nullable: true
    }
}
