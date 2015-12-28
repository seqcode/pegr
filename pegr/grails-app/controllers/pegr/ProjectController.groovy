package pegr
import grails.transaction.Transactional
import pegr.ProjectRole

@Transactional(readOnly = true)
class ProjectController {

	def springSecurityService
	
    def index() { 
        // get the current login user
		def currentUser = springSecurityService.currentUser
		// query the user's the projects
        def userProjects = ProjectUser.where {user==currentUser}
            .list(fetch: [project: 'eager'])
        userProjects.sort{a,b->b.project.lastUpdated<=>a.project.lastUpdated}
		
		[userProjects: userProjects]
	}
	
    @Transactional
    def create() {
        def user = springSecurityService.currentUser
        // create new project
        def project = new Project(params)
        if (project.validate() && project.save()) {
            // add current user as owner
            project.addToProjectUsers(new ProjectUser(user: user, 
                                                      project: project, 
                                                      projectRole: ProjectRole.OWNER)) 
            flash.message = "Successfully  created project ${project.name}"
        } else {
            flash.message = "Invalid project"
        }
    }
    
	def edit(){
		
	}
	
	def show() {
        
		[project: project]
	}
	
	def addUser(project, newUser) {
		// check is the current user is authorized to add new users to the project
		def user = springSecurityService.currentUser
		def projectRole = ProjectUser.where(user==user 
                                        && project==project).get(max:1)
		if(projectRole && projectRole == ProjectRole.OWNER) {
			def newProjectUser = new ProjectUser(project:project, 
                                                 user: newUser, 
                                                 projectRole: projectRole)
			newProjectUser.save()
			
			flash "newUser has been added to this project."
			redirect(action:"show", params: [project: project])
		} else {
			render status: 403 // response.sendError(403)
		}
	}
	
	def removeUser() {
		
	}
}
