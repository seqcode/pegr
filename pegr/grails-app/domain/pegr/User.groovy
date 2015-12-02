package pegr

class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String fullName
	String email
	String phone
	Lab lab
	Address address
	
	static hasMany = [projectUsers: ProjectUser]
	
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
		fullName nullable: true, maxSize: 30
		email email: true, nullable: true
		phone nullable: true, maxSize: 20
		lab nullable: true
		address nullable: true
	}

	static mapping = {
		password column: '`password`'
	}
}
