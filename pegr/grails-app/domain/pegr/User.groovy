package pegr

class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String fullName
	String email
	String phone
	Organization affiliation
	Address address
    String apiKey
	
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	@Override
	int hashCode() {
		username?.hashCode() ?: 0
	}

	@Override
	boolean equals(other) {
		is(other) || (other instanceof User && other.username == username)
	}

	@Override
	String toString() {
        if (fullName) {
            return "$username($fullName)" 
        } else {
            return "${username}"
        }
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}
    
    boolean isAdmin() {
        return this.authorities.any { it.authority == "ROLE_ADMIN" }
    }
    
    boolean isMember() {
        return this.authorities.any { it.authority == "ROLE_MEMBER" }
    }

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		fullName nullable: true, blank: true
		email nullable: true, blank: true
		phone nullable: true, maxSize: 20
		affiliation nullable: true
		address nullable: true
        apiKey nullable: true
	}

	static mapping = {
		password column: '`password`'
		dynamicUpdate true
	}
}
