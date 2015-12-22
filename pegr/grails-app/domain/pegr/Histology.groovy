package pegr

class Histology {

	Histology parent
	String name
	
    static constraints = {
		parent nullable: true
		name size: 2..30, unique: true
    }
}
