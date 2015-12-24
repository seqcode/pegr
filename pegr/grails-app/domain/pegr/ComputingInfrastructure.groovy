package pegr

class ComputingInfrastructure {

	String name
	
	String toString() {
		name
	}
    static constraints = {
    	name unique: true, maxSize: 20
	}
}
