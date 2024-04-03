package pegr

class ProjectController {
    
	def springSecurityService
    def projectService
    def sampleService
    def utilityService
	
    def index(int max, int offset) {
        max = Math.min(max ?:15, 100)
        // get the current login user
		def currentUser = springSecurityService.currentUser
		
        // query the user's the projects
        if (!params.sort) {
            params.sort = "dateCreated"
            params.order = "desc"
        }
        def c = ProjectUser.createCriteria()
        def projects = c.list(max: max, offset: offset) {
            eq("user", currentUser)
            project {
                order(params.sort, params.order)
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
                if (Project.findByName(project.name)) {
                    request.message = "Project with the same name already exists! Please choose a different project name."
                    render(view:'create')
                } else {
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
            def otherSamples = [] 
            
            def cohortSamples = currentProject.cohorts*.samples.flatten()
            currentProject.samples.each {
                if (!(it in cohortSamples)) {
                    otherSamples << it
                }
            }
            
            [project: currentProject, 
             links: utilityService.parseJson(currentProject.links),
             projectUsers: projectUsers, 
             projectEditAuth: projectEditAuth, 
             sampleEditAuth: sampleEditAuth,
             otherSamples: otherSamples
            ]
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
        render template:"userTable", model: [projectUsers: projectUsers, message: message, projectEditAuth: true]
	}
    
    def removeUser() {
        if (request.method == 'POST') {
            def projectId = params.projectId as Long 
            def userId = params.userId as Long

            projectService.removeUser(projectId, userId)
            redirect(action: "show", id:projectId)
        }
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
        render template:"userTable", model: [projectUsers: projectUsers, message: message, projectEditAuth: true]
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
    
    /**
     * Given the search string, search for the project with matching string in name or description.
     * @param str the search string
     * @param merge indicator. If the given value is "merge", the next step is to merge projects; 
     * else simply list the projects. 
     */
    def search(String str, String merge) {
        def c = Project.createCriteria()
        def listParams = [
                max: params.max ?: 25,
                sort: params.sort ?: "dateCreated",
                order: params.order ?: "desc",
                offset: params.offset
            ]
        def projects = c.list(listParams) {
            or {
                ilike "name", "%${str}%"
                ilike "description", "%${str}%"
            }
        }
        if (merge == "Merge") {
            def checkedCount = 0;
            if (session.checkedProject) {
                checkedCount = session.checkedProject.size()
            }
            render(view: "mergeSelect", model: [totalCount: projects.totalCount, projects: projects, str: str, checkedCount: checkedCount])
        } else {
            [projects: projects, totalCount: projects.totalCount, str: str] 
        }
    }
    
    /**
     * Merge the projects stored in session to the project in cmd. If the "merge to" project does not exist 
     * yet, a new project will be saved first. The users in cmd are added to the "merge to" project. After 
     * the merge, all references (except project_user) to the "merge from" projects
     * will be changed to the "merge to" project and the "merge from" projects will be removed.
     * @param cmd a class with project name and a list user and roles in the "merge to" project
     */
    def merge(UserRoleListCommand cmd) {
        if (session.checkedProject) {
            try {
                def mergeToProjectId = projectService.mergeProjects(cmd.projectName, session.checkedProject, cmd.userRoles)
                session.checkedProject = null
                flash.message = "Success!"
                redirect(action: "show", id: mergeToProjectId)
            } catch (Exception e) {
                flash.message = e.message 
                redirect(action: "showChecked")
            }
        } else {
            flash.message = "No projects selected to merge from!"
            redirect(action: "all")
        }
    }
        
    /**
     * Clear the session and return to the project/all page
     */
    def cancelMerge() {
        if (session.checkedProject) {
            session.checkedProject = null
        }        
        redirect(action: "all")
    }
    
    /**
     * Clear the stored project IDs in session
     */
    def clearCheckedProjectAjax(){
        if (session.checkedProject) {
            session.checkedProject = null
        }
        render true
    }

    /**
     * Given a project ID, store it in the session.
     * @param id project ID
     * Return the number of projects stored in the session.
     */
    def addCheckedProjectAjax(Long id) {
        if (!session.checkedProject) {
            session.checkedProject = []
        }
        if (!(id in session.checkedProject)) {
            session.checkedProject << id
        }
        render session.checkedProject.size()
    }
    
    /**
     * Given a project ID, remove it from the session.
     * Return the number of projects stored in the session.
     * @param id project ID
     */
    def removeCheckedProjectAjax(Long id) {
        if (id in session.checkedProject) {
            session.checkedProject.remove(id)
        }
        render session.checkedProject.size()
    }

    /**
     * Get project IDs stored from session and query the projects form the IDs.
     */
    def showChecked(){
        def projects = []
        if (session.checkedProject) {
            session.checkedProject.each {
                def project = Project.get(it)
                if (project) {
                    projects << Project.get(it)
                }
            }
        }
        if (projects.size() == 0) {
            flash.message = "No projects to merge!"
            redirect(action: "all")
        }
        
        [projects: projects]
    }
    
    def confirmUsersInMergedProject(String projectName) {
        def projects = []
        def mergeFromProjects = []
        if (session.checkedProject) {
            session.checkedProject.each {
                def project = Project.get(it)
                if (project) {
                    mergeFromProjects << Project.get(it)
                }
            }
        }
        if (mergeFromProjects.size() == 0) {
            flash.message = "No projects to merge!"
            redirect(action: "all")
        }
       
        // add mergeToProject
        def mergeToProject = Project.where {name == projectName}.get(max:1)
        if (mergeToProject && !projects.contains(mergeToProject)) {
            projects = [mergeFromProjects, mergeToProject]
        } else {
            projects = mergeFromProjects
        }
        
        def projectUsers = []
        projects.each { p ->
            def oldProjectUsers = ProjectUser.where {project == p}.list()
            if (oldProjectUsers && oldProjectUsers.size() > 0) {
                oldProjectUsers.each { pu ->
                    if (!projectUsers.collect { it.user }.contains(pu.user)) {
                        projectUsers << pu
                    }
                }
            }
        }
        
        // add current user
        def currUser = springSecurityService.currentUser
        if (!projectUsers.collect { it.user }.contains(currUser)) {
            projectUsers << new ProjectUser(user:currUser, 
                                              projectRole: ProjectRole.OWNER)
        }
        [projectName: projectName, mergeFromProjects: mergeFromProjects, projectUsers: projectUsers, mergeToProject: mergeToProject]
    }
    
    def updateCohortNotesAjax(Long cohortId, String notes) {
        projectService.updateCohortNotes(cohortId, notes)
        render notes
    }
    
    def editLinks(Long projectId) {
        def project = Project.get(projectId)
        if (project) {
            def projectEditAuth = projectService.projectEditAuth(project)
            if (projectEditAuth) {
                def links = utilityService.parseJson(project.links)
                if (links == null) {
                    links = [[name: "DOI", url: ""], 
                             [name: "GEO", url: ""], 
                             [name: "Github", url: ""], 
                             [name: "PMID", url: ""]]
                }
                [project: project, links: links]
            } else {
                render(view: "/403")
            }            
        } else {
            render(view: "/404")
        }
    }
        
    def updateLinks() {
        def project = Project.get(params["projectId"])
        if (project) {
            def projectEditAuth = projectService.projectEditAuth(project)
            if (projectEditAuth) {  
                project.links = params["links"]
                project.save()
                redirect(action: "show", id: params["projectId"])
            } else {
                render(view: "/403")
            }
        } else {
            render(view: "/404")
        }
    }
    
    
    def exportProjectSamples(Long projectId) {
        def data = ["SampleID,Description,Strain,Antibody,Target,Assay"]
        
        def project = Project.get(projectId)
        
        project.samples.each { sample ->
            data.add([sample.id, sample.naturalId, sample.cellSource, sample.antibody, sample.target, sample.assay].join(','))    
        }        
        
        def today = new Date()
        def filename = "ProjectID${projectId}_${today.year}-${today.month}-${today.day}"
        File file = File.createTempFile(filename, ".csv")
        file.deleteOnExit()
        file.text= data.join(System.lineSeparator())
        
        try {
            response.setContentType("application/csv")
            response.setHeader("Content-disposition", "attachment;filename=\"${filename}.csv\"")
            response.outputStream <<  file.getBytes()
            webRequest.renderView = false
        } catch(Exception e) {
            render "Error!"
        } finally {
            response.getOutputStream().flush()
            response.getOutputStream().close()
        }
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

class UserRoleCommand {
    Long userId
    String role
}

class UserRoleListCommand {
    String projectName
    List<UserRoleCommand> userRoles = [].withLazyDefault { new UserRoleCommand() } 
}