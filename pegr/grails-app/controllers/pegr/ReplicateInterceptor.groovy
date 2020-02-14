package pegr

class ReplicateInterceptor {

    def springSecurityService
    def projectService
    
    /**
     * This is executed before the following actions in replicate controller and checks authorization. 
     */
    boolean before() { 
        if (actionName == 'show') {
            def currUser = springSecurityService.currentUser
            if (currUser.isAdmin()) {
                return true
            }
            def replicateId = params.long('id')
            def project = ReplicateSet.get(replicateId)?.project
            if (!project) {
                return true
            }
            if (ProjectUser.where {project == project && user == currUser}.get(max: 1)) {
                return true                    
            } else {
                render(view: '/login/denied')
                return false
            }
        } else if (actionName in ['addSamples',
                                  'removeSample']) {
           def replicateId = params.long('setId')
            def project = ReplicateSet.get(replicateId)?.project
            if (projectService.sampleEditAuth(project)) {
                return true
            } else {
                render(view: '/login/denied')
                return false
            }
        } else if (actionName == 'delete') {
            def replicateId = params.long('id')
            def project = ReplicateSet.get(replicateId)?.project
            if (projectService.sampleEditAuth(project)) {
                return true
            } else {
                render(view: '/login/denied')
                return false
            }
        } else if (actionName == 'saveAjax') {
            def project = Project.get(params.long('projectId'))
            if (projectService.sampleEditAuth(project)) {
                return true
            } else {
                render(view: '/login/denied')
                return false
            }
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
