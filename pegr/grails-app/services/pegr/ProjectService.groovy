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
    void addUser(ProjectUser projectUser) {
        projectUser.save()
    }
    
    @Transactional
    void removeUser(Long projectId, Long userId) {
        def projectUser = ProjectUser.where{project.id == projectId && user.id == userId}.first()
        projectUser.delete()
    }
    
    @Transactional
    void addSamples() {
        
    }
}