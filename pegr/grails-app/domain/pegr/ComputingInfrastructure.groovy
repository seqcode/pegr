package pegr

class ComputingInfrastructure {

	String name
	
    static constraints = {
    	name unique: true, maxSize: 20
	}
}
