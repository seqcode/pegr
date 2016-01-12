package pegr

class Histology {

	Histology parent
	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		parent nullable: true
		name unique: true
    }
}
