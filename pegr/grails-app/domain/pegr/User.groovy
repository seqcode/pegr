package pegr

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
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
    
    String toString() {
        "${fullName} (${username})"
    }

	User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	Set<RoleGroup> getAuthorities() {
		UserRoleGroup.findAllByUser(this)*.roleGroup
	}
    
    Set<Role> getRoles() {
        return this.authorities*.authorities.flatten()
    }
    
    boolean isAdmin() {
        return this.roles.any { it.authority == "ROLE_ADMIN" }
    }
    
    boolean isMember() {
        return this.roles.any { it.authority == "ROLE_MEMBER" }
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
