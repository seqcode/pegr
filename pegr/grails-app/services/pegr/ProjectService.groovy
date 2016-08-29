package pegr
import grails.transaction.Transactional

class ProjectException extends RuntimeException {
    String message
}

class ProjectService {
    def springSecurityService
    def utilityService

    @Transactional
    void save(Project project, List fundings) {
        if (!project.save(flush: true)) {   
            throw new ProjectException(message: "Invalid inputs!")
        }
        
        // remove old project fundings
        ProjectFunding.executeUpdate("delete from ProjectFunding where project.id =:projectId", [projectId: project.id])
        
        // save the new fundings
        fundings.each { funding ->
            new ProjectFunding(project: project, funding: funding).save()
        }
    }
    
    @Transactional
    void saveWithUser(Project project, User user, List fundings) {
        // save the project and fundings
        save(project, fundings)
        
        // add current user as owner            
        new ProjectUser(user: user, 
                      project: project, 
                      projectRole: ProjectRole.OWNER).save()            

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
    
    /**
     * ADMIN, MEMBER and project users are allow to view the project
     */
    def projectViewAuth(Long projectId) {
        def authorized = false
        def currUser = springSecurityService.currentUser
        if (currUser.isAdmin() 
            || currUser.isMember()
            || (ProjectUser.where {project.id == projectId && user == currUser}.get(max: 1))) {
            authorized = true
        }
        return authorized
    }
    
    def projectEditAuth(Project project) {
        def authorized = false
        def currUser = springSecurityService.currentUser
        if (currUser.isAdmin()) {
            authorized = true
        } else if (ProjectUser.where { project == project && user == currUser && projectRole == ProjectRole.OWNER}.find()) {
           authorized = true                    
        }   
        return authorized
    }
    
    def sampleEditAuth(Project project) {
        def authorized = false
        def currUser = springSecurityService.currentUser
        if (currUser.isAdmin()) {
            authorized = true
        } else if (ProjectUser.where { project == project && user == currUser && projectRole == ProjectRole.OWNER}.find()) {
           authorized = true                    
        } else if (ProjectUser.where { project == project && user == currUser && projectRole == ProjectRole.PARTICIPANT}.find()) {
           authorized = true                    
        }     
        return authorized
    }
    
    @Transactional
    def saveNotes(Long projectId, String notes) {
        def project = Project.get(projectId)
        if (project) {
            project.notes = notes
            project.save(failOnError: true)
        } else {
            throw new ProjectException(message: "Project not found!")
        }
    }
}