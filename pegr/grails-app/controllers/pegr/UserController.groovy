package pegr
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

	static showInAdmin = true

	def springSecurityService	
	
	def profile(){
        def user = springSecurityService.currentUser
        [user:user]
	}
    
    @Transactional
    def editInfo() {
        if(request.method=='POST'){
            withForm {
                def user = springSecurityService.currentUser?.attach()
                user.properties = params
                if (user.validate() && user.save()) {
                    flash.message = "Successfully updated basic information!"
                    redirect(action: "profile")
                } else {
                    flash.message = "Invalid input!" 
                    render(view:'editInfo', model:[user: user])
                }
            }
        }else{
            def user = springSecurityService.currentUser
            [user: user]
        }
    }
    
    @Transactional
    def editAddress(Address address){
        if(request.method=='POST'){
            withForm {
                if (address.validate() && address.save()) {
                    def user = springSecurityService.currentUser?.attach()
                    user.address = address
                    flash.message = "Your address has been updated!"
                    redirect(action: "profile")
                } else {
                    flash.message = "Invalid input!" 
                    render(view:'editAddress', model:[address: address])
                }
            }
        }else{
            [address: address]
        }
    }
    
    @Transactional
    def deleteAddress(Address address) {
        if (address == null) {
            render status:404
        }
        def user = springSecurityService.currentUser?.attach()
        user.address = null
        address.delete()
        flash.message = "Your address has been deleted!"
        redirect(action: "profile")
    }
    
    @Transactional
    def updatePassword() {
        if(request.method=='POST') {
            withForm {
                def user = springSecurityService.currentUser.attach()
                def urc = new UserRegistrationCommand(username: user.username, 
                                                      password: params.password, 
                                                passwordRepeat: params.passwordRepeat)
                if (urc.hasErrors()) {
                    [user: urc]
                } else {
                    flash.message = "Your password has been changed."
                    user.password = springSecurityService.encodePassword(urc.password)
                    if (user.validate() && user.save()) {
                        redirect(action: "profile")
                    }else {
                        [user: urc]
                    }
                }        
            }   
        }
    }
    
    @Transactional
	def register(UserRegistrationCommand urc) {
        if(request.method=='POST') {
            if (urc.hasErrors()) {
                [user: urc]
            } else {
                user = new User(urc.properties)
                user.password = springSecurityService.encodePassword(urc.password)
                if (user.save()) {
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

	static constraints = {
		importFrom User

		password size: 5..20, blank: false		
		passwordRepeat nullable: false,
		   validator: { passwd2, urc ->
			   return passwd2 == urc.password ?: 'validation.reenterSamePassword'
		   }
	}
}
