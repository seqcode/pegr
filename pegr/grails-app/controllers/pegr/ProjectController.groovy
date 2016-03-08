package pegr

class ProjectController {
    
	def springSecurityService
    def projectService
	
    def index(int max) {
        params.max = Math.min(max ?:15, 100)

        // get the current login user
		def currentUser = springSecurityService.currentUser
		
        // query the user's the projects
        def query = ProjectUser.where {user == currentUser}
        def projects = query.list(params).collect{it.project}
        def totalCount = query.count()
        [projects: projects, totalCount: totalCount]
	}
    
    def all(int max) {
        params.max = Math.min(max ?:15, 100)
		
        // query all the projects
        def projects =  Project.list(params)
        [projects: projects, totalCount: Project.count()]
    }

    def create() {
        if(request.method == 'POST') {
            withForm{                
                // create new project
                def project = new Project(params)
                def user = springSecurityService.currentUser
                try {
                    projectService.saveWithUser(project, user)
                    flash.message = "Successfully  created project ${project.name}"
                    redirect(action:"show", id:"${project.id}")
                } catch (ProjectException e) {
                    request.mesage = e.message
                    render(view:'create', model:[project: project])
                } catch (Exception e) {
                    log.error "Error: ${e.message}", e
                    request.message ="Oops! Please try again."
                    render(view:'create', model:[project: project])
                }
            }        
        }
    }
    
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
                    projectService.save(project)
                    flash.message = "Successfully  updated information for project ${project.name}"
                    redirect(action:"show", id:"${project.id}")
                } catch(ProjectException e) {
                    reqeust.message = e.message
                    [project: project]
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
    
	def show(Long id) {
		def currentProject = Project.get(id)
        if (currentProject) {
            def projectUsers = ProjectUser.where { project==currentProject}.list()
            [project: currentProject, projectUsers: projectUsers, sampleCount: currentProject.samples.size()]
        } else {
            flash.message = "Project not found!"
            redirect(action: "index")
        }
	}
	
	def addUserAjax() {
        try {
            def projectUser = new ProjectUser(params)
            projectService.addUser(projectUser)			
			def projectUsers = ProjectUser.where { project.id==params.project.id}.list()
			render template:"userTable", bean: projectUsers
        }catch(Exception e){
            log.error "Error: ${e.message}", e
			render status: 500
            render "<div class='errors'>An error has occurred</div>"
        }
	}

	def removeUserAjax(Long projectId, Long userId) {
		try {
			projectService.removeUser(projectId, userId)
			def projectUsers = ProjectUser.where { project.id==projectId}.list()
			render template:"userTable", bean: projectUsers
		}catch(Exception e) {
			log.error "Error: ${e.message}", e
			render status: 500
			render "<div class='errors'>An error has occurred</div>"
		}				
	}
    
    def addSamples() {
        
    }
}
