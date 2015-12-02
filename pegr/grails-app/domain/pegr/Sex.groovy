package pegr

class Sex {
	String name

    static constraints = {
		name unique: true, size: 2..20	
    }
}
