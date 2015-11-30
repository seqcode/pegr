package pegr

class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String fullName
	String email
	Organization organization
	
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
		return "$fullName ($username)" 
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		fullName blank: false		
		email email: true, blank: false
	}

	static mapping = {
		password column: '`password`'
	}
}
