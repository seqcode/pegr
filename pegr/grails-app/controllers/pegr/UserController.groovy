package pegr
import grails.transaction.Transactional
import grails.converters.*

class UserController {

	def springSecurityService	
	def userService
    def utilityService
    def emailService
    
	def profile(){
        def user = springSecurityService.currentUser
        if (!user) {
            redirect(uri: "/login/form")
        } else {
            [user:user]
        }
	}
    
    def editInfo(UserInfoCommand uic) {
        if(request.method=='POST'){
            withForm {                
				try {
                    int id = springSecurityService.currentUser.id
				    userService.updateUser(uic, id)
                    flash.message = "Successfully updated basic information!"
	                redirect(action: "profile")
				} catch(UserException e) {
					request.message = e.message
					render(view:'editInfo', model:[user: uic])
				}
            }
        }else{
            def user = springSecurityService.currentUser
            uic = new UserInfoCommand(user.properties['fullName', 'email', 'phone'])
            [user: uic]
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
                    request.message = "Invalid input!" 
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
            try {
                userService.create(urc)
                redirect(controller: "auth", action: "form")
            } catch (UserException e) {
                request.message = e.message
                [user: urc]
            }
        }
	}
    
    def generateApiKey() {
        try {
            userService.generateApiKey()
        } catch (UserException e) {
            flash.message = e.message
        }
        redirect(action: "profile")
    }
    
    def fetchUserAjax() {
        def users = User.list().collect{[it.id, it.toString()]}
        render utilityService.arrayToSelect2Data(users) as JSON
    }
    
    def email() {
        emailService.getLabels()
        redirect(action: "profile")
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

class UserInfoCommand {
    String fullName
    String email
    String phone
    static constraints = {
		importFrom User
    }
}