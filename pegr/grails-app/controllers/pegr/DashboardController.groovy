package pegr

class DashboardController {

	def springSecurityService
	
    def index() { 
		def user = springSecurityService.currentUser
		render view: "dashboard"
	}
}
