package pegr

class ProjectInterceptor {
    
    def projectService
    def springSecurityService
    
    boolean before() { 
        if (actionName == 'show') {
            def projectId = params.long('id')
            if (projectService.projectViewAuth(projectId)) {
                return true                    
            } else {
                render(view: '/login/denied')
                return false
            }
            return false 
        } else if (actionName in ['edit|addUserAjax',
                                  'removeUserAjax',
                                  'editUserRoleAjax']) {
            def project = Project.get(params.long('projectId'))
            if (projectService.projectEditAuth(project)) {
                return true                  
            } else {
                render(view: '/login/denied')
                return false
            }
        } else if (actionName in ['removeSample',
                                  'searchSample',
                                  'addExistingSample',
                                  'addNewSamples']) {
            def project = Project.get(params.long('projectId'))
            if (projectService.sampleEditAuth(project)) {
                return true                  
            } else {
                render(view: '/login/denied')
                return false
            }
        } else if (actionName in ['showChecked', 
                                  'merge']) {
            def currUser = springSecurityService.currentUser
            def auth = true
            if (!currUser.isAdmin() && session.checkedProject) {
                def projects = session.checkedProject
                if (params.projectName) {
                    def mergeToProject = Project.where {name == params.projectName}.get(max:1)
                    if (mergeToProject) {
                        projects.push(mergeToProject.id)    
                    }    
                }
                projects.each { projectId ->
                    def projectUser = ProjectUser.where {project.id == projectId && user.id == currUser.id}.find()
                    if (!projectUser || projectUser.projectRole != ProjectRole.OWNER) {
                        auth = false
                    }
                }                     
            }
            if (auth == false) {
                session.checkedProject = null
                render(view: '/login/denied')
            }
            return auth
        } else {
            return true
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
