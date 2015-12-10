package pegr

class DashboardController {

	def springSecurityService
	
    def index() { 
		def user = springSecurityService.currentUser
		
		// query the user's the projects

		
		render view: "dashboard"
	}
}
