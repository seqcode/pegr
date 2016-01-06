package pegr
import grails.transaction.Transactional
import pegr.ProjectRole

@Transactional(readOnly = true)
class ProjectController {
    
	def springSecurityService
	
    def index() {
        def max = Math.min(params.max ?:10, 100)
        // get the current login user
		def currentUser = springSecurityService.currentUser
		// query the user's the projects
        // TODO: offset, max
        def userProjects = ProjectUser.where {user==currentUser}

       // userProjects.sort{a,b->b.project.lastUpdated<=>a.project.lastUpdated}
		def projectCount = ProjectUser.where {user==currentUser}.count()
		[userProjects: userProjects, projectCount: projectCount]
	}
	
    @Transactional
    def create() {
        if(request.method == 'POST') {
            withForm{
                def user = springSecurityService.currentUser
                // create new project
                def project = new Project(params)
                if (project.validate() && project.save()) {
                    // add current user as owner
                    project.addToProjectUsers(new ProjectUser(user: user, 
                                                              project: project, 
                                                              projectRole: ProjectRole.OWNER)) 
                    flash.message = "Successfully  created project ${project.name}"
                    redirect(action:"show", id:"${project.id}")
                } else {
                    flash.message = "Invalid project"
                    render(view:'create', model:[project: project])
                }
            }        
        }
    }
	
	def show() {
		def currentProject = Project.get(params.id)
        def projectUsers = ProjectUser.where { project==currentProject}.list()
        [project: currentProject, projectUsers: projectUsers]
	}
	
	def addUser(project, newUser) {
		
	}
	
	def removeUser() {
		
	}
}
