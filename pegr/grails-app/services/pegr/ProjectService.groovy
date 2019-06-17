package pegr
import groovy.sql.Sql
import grails.transaction.Transactional

class ProjectException extends RuntimeException {
    String message
}

class ProjectService {
    def springSecurityService
    def utilityService
    def dataSource
    
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
    
    @Transactional
    def delete(Project project) {
        def projectId = project.id
        ProjectBags.executeUpdate("delete from ProjectBags where project.id =:projectId", [projectId: projectId])
        ProjectFunding.executeUpdate("delete from ProjectFunding where project.id =:projectId", [projectId: projectId])
        ProjectSamples.executeUpdate("delete from ProjectSamples where project.id =:projectId", [projectId: projectId])
        ProjectUser.executeUpdate("delete from ProjectUser where project.id =:projectId", [projectId: projectId])
        Item.executeUpdate("update Item set project = null where project.id=:projectId", [projectId: projectId])
        ReplicateSet.executeUpdate("update ReplicateSet set project = null where project.id =:projectId", [projectId: projectId])        
        SequencingCohort.executeUpdate("delete from SequencingCohort where project.id=:projectId", [projectId:projectId])
        project.delete()
    }
    
    @Transactional
    def mergeProjects(String mergeToProjectName, List mergeFromProjectIds,  List userRoles) {
        def currUser = springSecurityService.currentUser
        
        // check if the mergeTo project already exists
        def mergeToProject = Project.where {name == mergeToProjectName}.get(max:1)
        
        // if the mergeToProject does not exit, create one
        if (!mergeToProject) {
            mergeToProject = new Project(name: mergeToProjectName)
            if (!mergeToProject.save(flush: true)) {   
                throw new ProjectException(message: "Invalid ProjectName!")
            }
        }
        
        // merge
        mergeFromProjectIds.each { fromId ->
            if (fromId != mergeToProject.id) {
                mergeProject(fromId, mergeToProject) 
            }
        }
        
        ProjectUser.executeUpdate("delete ProjectUser where project.id=?", [mergeToProject.id])
        userRoles.each {userRole ->
            def userToAdd = User.get(userRole.userId)

            if (userToAdd) {
                def existingProjectUser = ProjectUser.where{ project == mergeToProject && user == userToAdd}.find()
                if (userRole.role != "null" && !existingProjectUser) {
                    new ProjectUser(project: mergeToProject,
                                   user: userToAdd,
                                   projectRole: userRole.role).save(flush:true, failOnError: true)
                }
            }
        }
        
        return mergeToProject.id
    }
    
    @Transactional
    def mergeProject(Long fromId, Project toProject) {
        def fromProject = Project.get(fromId)
        def toId = toProject.id
        def sql = new Sql(dataSource)
        try {
            ["history", "item", "replicate_set", "sequencing_cohort"].each { table ->
                utilityService.updateForeignKeyInDb(table, "project", fromId, toId, sql)
            }

            utilityService.updateLinksInDb("project_samples", "sample", 'project', fromId, toId, sql)
            utilityService.updateLinksInDb("project_funding", "funding", 'project', fromId, toId, sql)
            utilityService.updateLinksInDb("project_bags", "bag", 'project', fromId, toId, sql)

            sql.execute("delete from project_user where project_id = ?", [fromId])
            sql.close()
        } catch(Exception e) {
            sql.close()
            log.error e
            throw new ProjectException(message: "Error merging project ${fromId} to project ${toProject.id}!")
        }
        
        fromProject.delete()
    }
    
    
    

}