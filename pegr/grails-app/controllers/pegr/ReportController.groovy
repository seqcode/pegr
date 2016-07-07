package pegr

class ReportController {
    
    def springSecurityService
    def projectService
    def sampleService

    def index() { 
        render "Hello World"
    }
    
    def show(Long id) {
        def currentProject = Project.get(id)
        if (currentProject) {
            def projectUsers = ProjectUser.where { project==currentProject}.list()
            def authorized = false
            def currUser = springSecurityService.currentUser
            if (currUser.isAdmin()) {
                authorized = true
            } else if (projectUsers.find { it.user == currUser && it.projectRole == ProjectRole.OWNER}) {
               authorized = true                    
            }          
            def samples = ProjectSamples.where {project==currentProject}.list(params).collect{it.sample}
            def experiments = samples.collect{it.sequencingExperiments}.flatten()
            def alignments = experiments.collect{it.alignments}.flatten()
            [project: currentProject, projectUsers: projectUsers, samples: samples, experiments: experiments, alignments: alignments, sampleCount: currentProject.samples.size(), authorized: authorized]
        } else {
            flash.message = "Project not found!"
            redirect(action: "index")
        }
    }
}
