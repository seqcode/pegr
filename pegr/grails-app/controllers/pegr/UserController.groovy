package pegr

class UserController {

	static showInAdmin = true

	def springSecurityService	
	
	def profile(){
		
	}
	
	def register(UserRegistrationCommand urc) {
		if(urc.hasErrors()) {
			render view:"register", model: [user:urc]
		}else{
			def user = new User(urc.properties)
			user.password = springSecurityService.encodePassword(urc.password)
			user.address = new Address(urc.properties)
			if(user.validate() && user.save()) {
				flash.message = "Welcome abroard, ${urc.fullName ?: urc.username}"
				redirect(uri: '/')
			} else {
				return [user: urc]
			}
		}
		
	}
	
}

class UserRegistrationCommand {
	String username
	String password
	String passwordRepeat
	String fullName
	String email
	String phone
	
	// Address
	String line1
	String line2
	String city
	String state
	String postalCode
	String country
	
	static constraints = {
		importFrom User
		importFrom Address
		password(size: 5..20, blank: false)		
		passwordRepeat(nullable: false,
		   validator: { passwd2, urc ->
			   return passwd2 == urc.password
		   })
	}
}
