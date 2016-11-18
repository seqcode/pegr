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
    
    def editAddress(Address newAddress){
        def user = springSecurityService.currentUser
        if(request.method=='POST'){
            withForm {
                try {
                    userService.updateAddress(user, newAddress)
                    flash.message = "Your address has been updated!"
                    redirect(action: "profile")
                } catch(UserException e) {
                    request.message = e.message
                    render(view:'editAddress', model:[address: newAddress])
                }
            }
        }else{
            [address: user.address]
        }
    }
    
    def deleteAddress() {
        try {
            def user = springSecurityService.currentUser
            userService.deleteAddress(user)
        } catch (UserException e) {
            flash.message = "Your address has been deleted!"
        }        
        redirect(action: "profile")
    }
    
    def updatePassword(UserRegistrationCommand urc) {
        if(request.method=='POST') {
            withForm {
                def user = springSecurityService.currentUser.attach()
                urc.username = user.username
                urc.validate()
                if (urc.hasErrors()) {
                    render(view: "updatePassword", model: [user: urc])
                } else {                                   
                    user.password = springSecurityService.encodePassword(urc.password)
                    try {
                        userService.save(user)
                        flash.message = "Your password has been changed."
                        redirect(action: "profile")
                    } catch(UserException e) {
                        request.message = e.message
                        render(view: "updatePassword", model: [user: urc])
                    }
                }        
            }   
        }
    }
    
	def register(UserRegistrationCommand urc) {
        if(request.method=='POST') {
            try {
                userService.create(urc)
                flash.message = "You have registered with PEGR. Please login."
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

@grails.validation.Validateable
class UserRegistrationCommand {
	String username
	String password
	String passwordRepeat

	static constraints = {
		importFrom User

		password(size: 5..20, blank: false)		
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