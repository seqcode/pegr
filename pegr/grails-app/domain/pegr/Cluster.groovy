package pegr

class Cluster {

	String name
	
    static constraints = {
    	name unique: true, maxSize: 20
	}
}
