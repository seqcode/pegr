package pegr
import grails.transaction.Transactional

class ProjectException extends RuntimeException {
    String message
}

class ProjectService {
    def springSecurityService
    def utilityService

    @Transactional
    void save(Project project) {
        if (!project.save(flush: true)) {   
            throw new ProjectException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void saveWithUser(Project project, User user) {
        if (project.save(flush: true)) {
            // add current user as owner            
            new ProjectUser(user: user, 
                          project: project, 
                          projectRole: ProjectRole.OWNER).save()            
        } else {
            throw new ProjectException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void saveUser(ProjectUser projectUser) {
        projectUser.save()
    }
    
    @Transactional
    void removeUser(Long projectId, Long userId) {
        def projectUser = ProjectUser.where{project.id == projectId && user.id == userId}.first()
        projectUser.delete()
    }
    
    @Transactional
    void removeSample(Long sampleId, Long projectId) {
        def projectSample = ProjectSamples.where {project.id == projectId && sample.id == sampleId}.find()
        if (projectSample) {
            projectSample.delete()
        } else {
            throw new ProjectException("The selected sample is not in the project!")
        }
    }
    
    @Transactional
    def addExistingSamples(Long projectId, String sampleIds) {
        def project = Project.get(projectId)
        if (!project) {
            throw new ProjectException(message: "Project not found!")
        }
        def messages = []
        def addedIds = []
        def ids
        try {
            ids = utilityService.parseSetOfNumbers(sampleIds)
        } catch (UtilityException e) {
            throw new ProjectException(message: e.message)
        }
        def unknownSampleIds = []
        ids.each{ sampleId ->
            def sample = Sample.get(sampleId)
            if (!sample) {
                unknownSampleIds << sampleId
            } else if (!ProjectSamples.findByProjectAndSample(project, sample)){
                new ProjectSamples(project: project, sample: sample).save(failOnError: true)
            }
        }
        return unknownSampleIds
    }
    
    def authToEdit(Project project) {
        def authorized = false
        def currUser = springSecurityService.currentUser
        if (currUser.isAdmin()) {
            authorized = true
        } else if (ProjectUser.where { project == project && user == currUser && projectRole == ProjectRole.OWNER}.find()) {
           authorized = true                    
        }   
        return authorized
    }
    
}