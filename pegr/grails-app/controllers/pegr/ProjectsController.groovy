package pegr

class ProjectsController {

	def springSecurityService
	
    def index() { 
		def user = springSecurityService.currentUser
		
		// query the user's the projects

		
		render view: "index"
	}
	
	def show() {
		render view: "show"
	}
}
