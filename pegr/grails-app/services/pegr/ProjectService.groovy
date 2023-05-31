package pegr
import groovy.json.*
import groovy.sql.Sql
import grails.gorm.transactions.Transactional

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
     * ADMIN, MEMBER and project users are allowed to view the project
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
        if (!project) {
            return true
        }
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
        
        // merge project one at a time
        mergeFromProjectIds.each { fromId ->
            if (fromId != mergeToProject.id) {
                mergeProject(fromId, mergeToProject) 
            }
        }
        
        // merge cohorts
        def cohortGroups = SequencingCohort.findAllByProject(mergeToProject).groupBy({cohort -> cohort.run})
        
        for (entry in cohortGroups) {
            def run = entry.key 
            def cohorts = entry.value
            
            def mergeToCohort = cohorts[0]
            def cohortName = "${run.id}_${mergeToProject.name}"
            if (mergeToCohort.name != cohortName) {
                mergeToCohort.name = cohortName
            }
            
            if (cohorts.size() > 1) {                
                def mergedImageMap = [:]
                def mergedNotes = ''
                for (cohort in cohorts) {
                    // get all images
                    def imageMap = utilityService.parseJson(cohort.images)
                    if (imageMap) {
                        imageMap.each { type, filepaths ->
                            if (!mergedImageMap[type]) {
                                mergedImageMap[type] = []
                            }
                            filepaths.each { filepath ->
                                mergedImageMap[type] << filepath
                            }                        
                        }
                    }
                
                    // get all notes
                    if (cohort.notes != null && cohort.notes != '') {
                        mergedNotes += ' ' + cohort.notes
                    }
                    
                    // update all sequencing_experiemnts
                    if (mergeToCohort.id != cohort.id) {
                        SequencingExperiment.executeUpdate("update SequencingExperiment set cohort.id=:mergeId where cohort.id=:thisId", [mergeId: mergeToCohort.id, thisId: cohort.id])
                        cohort.delete()
                    }   
                }
                mergeToCohort.images = JsonOutput.toJson(mergedImageMap)
                mergeToCohort.notes = mergedNotes
            }
            mergeToCohort.save()
        }    
        
        // merge project users
        ProjectUser.executeUpdate("delete ProjectUser where project.id=:projectId", [projectId: mergeToProject.id])
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
            ["history", "item", "sequencing_cohort"].each { table ->
                utilityService.updateForeignKeyInDb(table, "project", fromId, toId, sql)
            }

            utilityService.updateLinksInDb("project_samples", "sample", 'project', fromId, toId, sql)
            utilityService.updateLinksInDb("project_funding", "funding", 'project', fromId, toId, sql)
            utilityService.updateLinksInDb("project_bags", "bag", 'project', fromId, toId, sql)

            sql.execute("delete from project_user where project_id =:projectId", [projectId: fromId])
            sql.close()
        } catch(Exception e) {
            sql.close()
            log.error e.toString()
            throw new ProjectException(message: "Error merging project ${fromId} to project ${toProject.id}!")
        }
        
        fromProject.delete()
    }
    
    @Transactional
    def updateCohortNotes(Long cohortId, String notes) {
        def cohort = SequencingCohort.get(cohortId)
        if (!cohort) {
            throw new ProjectException(message: "Sequencing cohort not found!")
        }
        notes = notes.trim()
        if (notes == "") {
            notes = null
        }
        cohort.notes = notes
        cohort.save()
    }
    

}