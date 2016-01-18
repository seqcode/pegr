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
                try {
                    if (project.save(flush: true)) {
                        // add current user as owner
                        project.addToProjectUsers(new ProjectUser(user: user, 
                                                                  project: project, 
                                                                  projectRole: ProjectRole.OWNER)) 
                        flash.message = "Successfully  created project ${project.name}"
                        redirect(action:"show", id:"${project.id}")
                    } else {
                        reqeust.message = "Invalid inputs!"
                        render(view:'create', model:[project: project])
                }
                }catch(Exception e) {
                    log.error "Error: ${e.message}", e
                    request.message ="Oops! Please try again."
                    render(view:'create', model:[project: project])
                }
            }        
        }
    }
	
    @Transactional
    def edit() {
        def project = Project.get(params.id)
        if (!project) {
            flash.message = "Project no longer exists!"
            redirect(action:"index")
        }

        if(request.method == 'POST') {
            withForm{
                project.properties = params
                try {
                    if (project.save(flush: true)) {
                        flash.message = "Successfully  updated information for project ${project.name}"
                        redirect(action:"show", id:"${project.id}")
                    } else {
                        reqeust.message = "Invalid inputs!"
                        [project: project]
                    }
                } catch(Exception e) {
                    log.error "Error: ${e.message}", e
                    request.message ="Oops! Please try again."
                    [project: project]
                }
            }
        }else {
            [project: project]
        }
    }
    
	def show() {
		def currentProject = Project.get(params.id)
        def projectUsers = ProjectUser.where { project==currentProject}.list()
        [project: currentProject, projectUsers: projectUsers, sampleCount: currentProject.samples.size()]
	}
	
    @Transactional
	def addUserAjax() {
        try {
            def projectUser = new ProjectUser(params)
            projectUser.save(flush: true)			
			def projectUsers = ProjectUser.where { project.id==params.project.id}.list()
			render template:"userTable", bean: projectUsers
        }catch(Exception e){
            log.error "Error: ${e.message}", e
			render status: 500
            render "<div class='errors'>An error has occurred</div>"
        }
	}

    @Transactional
	def removeUserAjax(int projectId, int userId) {
		try {
			def projectUser = ProjectUser.where{project.id == projectId && user.id == userId}.first()
			projectUser.delete(flush: true)
			def projectUsers = ProjectUser.where { project.id==projectId}.list()
			render template:"userTable", bean: projectUsers
		}catch(Exception e) {
			log.error "Error: ${e.message}", e
			render status: 500
			render "<div class='errors'>An error has occurred</div>"
		}
				
	}
}
