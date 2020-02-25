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
            redirect(uri: "/login/auth")
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

    def updatePassword(PasswordRegistrationCommand urc) {
        if(request.method=='POST') {
            withForm {
                def user = springSecurityService.currentUser.attach()
                try {
                    userService.updatePassword(user, urc)
                    flash.message = "Your password has been changed."
                    redirect(action: "profile")
                } catch (UserException e) {
                    request.message = e.message
                    render(view: "updatePassword", model: [user: urc])
                }
            }
        }
    }

    def register(UserRegistrationCommand urc) {
        if(request.method=='POST') {
            try {
                userService.unlockAccount(urc)
                flash.message = "Your account has been activated!"
                redirect(uri: "/login/auth")
            } catch(UserException e) {
                request.message = e.message
                [token: urc.token]
            }
        } else {
            [token: urc.token]
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
        def users = User.list().collect{[it.id, it.username.toString()]}
        render utilityService.arrayToSelect2Data(users) as JSON
    }

    def forgetPassword() {

    }

    def sendResetPasswordEmail(String email){
        try {
            def token = userService.getToken(email)
            def url = g.createLink(action: "resetPassword", params: [token: token], absolute: true)
            sendMail {
               to email
               subject "[PEGR] Reset password"
               body 'Please follow the link ' + url + ' to reset your password.'
            }
        } catch (UserException e) {
            flash.message = e.message
            redirect(action: "forgetPassword")
        }
    }

    def resetPassword(String token, PasswordRegistrationCommand urc) {
        if (request.method == "POST") {
            try {
                userService.resetPasswordByToken(token, urc)
                redirect(controller: "login", action: "auth")
            } catch(UserException e) {
                request.message = e.message
                [token: token]
            }
        } else {
            [token: token]
        }
    }
}

class UserRegistrationCommand implements grails.validation.Validateable {
    String token
	String username
    String email
	String password
	String passwordRepeat

	static constraints = {
		importFrom User
        email email: true, blank: false
		password(size: 5..20, blank: false)
		passwordRepeat nullable: false,
		   validator: { passwd2, urc ->
			   return passwd2 == urc.password ?: 'validation.reenterSamePassword'
		   }
	}
}

class PasswordRegistrationCommand implements grails.validation.Validateable {
	String password
	String passwordRepeat

	static constraints = {
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
