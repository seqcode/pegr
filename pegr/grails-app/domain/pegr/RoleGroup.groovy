package pegr

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='name')
@ToString(includes='name', includeNames=true, includePackage=false)
class RoleGroup implements Serializable {

	private static final long serialVersionUID = 1

	String name

	String toString() {
		name
	}
    
	RoleGroup(String name) {
		this()
		this.name = name
	}

	Set<Role> getAuthorities() {
		RoleGroupRole.findAllByRoleGroup(this)*.role
	}

	static constraints = {
		name blank: false, unique: true
	}

	static mapping = {
		cache true
	}
}
