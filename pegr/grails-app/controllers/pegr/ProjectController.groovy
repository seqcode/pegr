package pegr

class ProjectController {
    
	def springSecurityService
    def projectService
    def sampleService
    def replicateService
	
    def index(int max, int offset) {
        max = Math.min(max ?:15, 100)
        // get the current login user
		def currentUser = springSecurityService.currentUser
		
        // query the user's the projects
        def c = ProjectUser.createCriteria()
        def projects = c.list(max: max, offset: offset) {
            eq("user", currentUser)
            project {
                order("dateCreated", "desc")
            }
        }.collect{it.project}
        // get the total count of projects linked to the user
        def totalCount = ProjectUser.where {user == currentUser}.count()
        [projects: projects, totalCount: totalCount]
	}
    
    def all(int max) {
        params.max = Math.min(max ?:15, 100)
		if (!params.sort) {
            params.sort = "dateCreated"
            params.order = "desc"
        }
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
                    def fundings = params.list("funding")
                    projectService.saveWithUser(project, user, fundings)
                    flash.message = "Successfully  created project ${project.name}"
                    redirect(action:"show", id:"${project.id}")
                } catch (ProjectException e) {
                    request.message = e.message
                    render(view:'create', model:[project: project])
                } catch (Exception e) {
                    log.error "Error: ${e.message}", e
                    request.message ="Oops! Please try again."
                    render(view:'create', model:[project: project])
                }
            }        
        }
    }
    
    def edit(Long projectId) {
        def project = Project.get(projectId)
        if (!project) {
            flash.message = "Project no longer exists!"
            redirect(action:"index")
        }

        if(request.method == 'POST') {
            withForm{
                project.properties = params
                try {
                    def fundings = params.list("funding")
                    projectService.save(project, fundings)
                    flash.message = "Successfully  updated information for project ${project.name}"
                    redirect(action:"show", id:projectId)
                } catch(ProjectException e) {
                    request.message = e.message
                    render(view: 'edit', model: [project: project])
                } catch(Exception e) {
                    log.error "Error: ${e.message}", e
                    request.message ="Oops! Please try again."
                    render(view: 'edit', model: [project: project])
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
            def projectEditAuth = projectService.projectEditAuth(currentProject)
            def sampleEditAuth = projectService.sampleEditAuth(currentProject)
            def replicates = replicateService.getReplicates(currentProject)
            [project: currentProject, 
             projectUsers: projectUsers, 
             replicates: replicates, 
             projectEditAuth: projectEditAuth, 
             sampleEditAuth: sampleEditAuth]
        } else {
            flash.message = "Project not found!"
            redirect(action: "index")
        }
	}
	
	def addUserAjax(Long projectId, Long userId, String projectRole) {
        def message = null
        try {
            def projectUser = new ProjectUser(project: Project.load(projectId), user: User.load(userId), projectRole: projectRole)
            projectService.saveUser(projectUser)				
        }catch(Exception e){
            message = "Error adding the user!"
        }
        def projectUsers = ProjectUser.where { project.id==projectId}.list()
        render template:"userTable", model: [projectUsers: projectUsers, message: message, authorized: true]
	}

	def removeUserAjax(Long projectId, Long userId) {
        def message = null
		try {
			projectService.removeUser(projectId, userId)
		}catch(Exception e) {
			message = "Error removing the user!"
		}				
        def projectUsers = ProjectUser.where { project.id==projectId}.list()
        render template:"userTable", model: [projectUsers: projectUsers, message: message, authorized: true]
	}
    
    def editUserRoleAjax(Long projectId, Long userId, String projectRole) {
        def message = null
        def projectUser = ProjectUser.where{project.id == projectId && user.id == userId}.get(max:1)
        if(projectUser) {
            projectUser.projectRole = projectRole
            projectService.saveUser(projectUser)
        } else {
            message = "User not found in this project!"
        }
        def projectUsers = ProjectUser.where { project.id==projectId}.list()
        render template:"userTable", model: [projectUsers: projectUsers, message: message, authorized: true]
    }
    
    def searchSample(Long projectId) {
        def project = Project.get(projectId)
        [project: project]
    }
    
    def addExistingSamples(Long projectId, String sampleIds) {
        try {
            def unknownSampleIds = projectService.addExistingSamples(projectId, sampleIds)
            if (unknownSampleIds.size() > 0) {
                flash.messages = "Unknown Samples ${unknownSampleIds} are not added to the project!"
            } else {
                flash.message = "Success! All samples have been added to the project."
            }
        } catch (ProjectException e) {
            flash.message = e.message
        } catch (Exception e) {
            flash.message = "An unexpected error has occured!"
            log.error e
        }
        redirect(action: "show", id: projectId)
    }
    
    def addNewSamples(Long projectId, Long assayId) {
        def project = Project.get(projectId)
        def assay = Assay.get(assayId)
        [project: project, assay: assay]
    }
    
    def removeSample(Long sampleId, Long projectId) {
        try {
            projectService.removeSample(sampleId, projectId)
        } catch(Exception e) {
            flash.message = e.message
        }
        redirect(action: "show", id: projectId)
    }
    
    def saveNewSamples(SampleListCommand command) {
        try {
            def message = sampleService.saveNewSamples(command.assayId, command.projectId, command.samples)
            flash.message = message
            redirect(action: "show", id: command.projectId)
        } catch(ProjectException e) {
            flash.message = e.message
            redirect(action: "addNewSamples", params:[projectId: command.projectId, assayId: command.assayId])
        }
    }
    
    def saveNotesAjax(Long projectId, String notes) {
        projectService.saveNotes(projectId, notes)
        render "success"
    }
    
}


class SampleCommand {
    Long providerId
    Long providerLabId
    String bioSourceId
    Long sendToId
    String genus
    String speciesId
    String parentStrain
    String strain
    String genotype
    String mutation
    String tissue
    String age
    String sex
    String histology
    String growthMedia
    String treatments
    String chrom
    String cellNum
    String volume
    String requestedTags
    String genomes
    String indices
    String sampleNotes
    String company
    String catalogNumber
    String lotNumber
    String abHost
    String immunogene
    String clonal
    String igType
    String abConcentration
    String abNotes
    String abVolumePerSample
    String ugPerChip
    String ulPerChip
    String targetType
    String target
    String cterm
    String nterm
    String indexType
}

class SampleListCommand {
    Long assayId
    Long projectId
    List<SampleCommand> samples = [].withLazyDefault { new SampleCommand() } 
}