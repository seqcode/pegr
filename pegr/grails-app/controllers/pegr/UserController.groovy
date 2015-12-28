package pegr
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

	static showInAdmin = true

	def springSecurityService	
	
	def profile(){
	}
    
    @Transactional
    def update(){
        def user = springSecurityService.currentUser.attach()
        user.properties = params
        if (user.validate() && user.save()) {
            flash.message = "Successfully updated profile!"
        } else {
            flash.message = "Invalid input!"
        }
            
    }
	
    @Transactional
	def register(UserRegistrationCommand urc) {
        if(request.method=='POST') {
            if (urc.hasErrors()) {
                [user: urc]
            } else {
                def user = new User(urc.properties)
                user.password = springSecurityService.encodePassword(urc.password)
                if (user.validate() && user.save()) {
                    redirect(uri: '/')
                }else {
                    [user: urc]
                }
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

	static constraints = {
		importFrom User

		password size: 5..20, blank: false		
		passwordRepeat nullable: false,
		   validator: { passwd2, urc ->
			   return passwd2 == urc.password ?: 'validation.reenterSamePassword'
		   }
	}
}
