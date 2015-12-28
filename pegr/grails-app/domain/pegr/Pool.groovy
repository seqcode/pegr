package pegr

class Pool {

	Date dateCreated
	User user
	
    static constraints = {

    }
    
    static mapping = {
        sort dateCreated:"desc"
    }
}
