package pegr
import grails.transaction.Transactional

class ProjectException extends RuntimeException {
    String message
}

class ProjectService {
    def springSecurityService
    
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
    List addExistingSamples(Long projectId, String sampleIds) {
        def messages = []
        def addedIds = []
        def idList = sampleIds.split(',')*.trim()
        idList.each{
            try {
                def sampleId = Long.parseLong(it)
                def project = Project.get(projectId)
                def sample = Sample.get(sampleId)
                new ProjectSamples(project: project, sample: sample).save()
                addedIds.push(sample.id)
            } catch (Exception e) {
                messages.push("Error adding Sample ${it}: ${e.message};")
            }
        }
       if (addedIds.size()) {
            messages.push("Samples [${addedIds.join(',')}] have been added to the project!")
        }
        return messages
    }
    
}