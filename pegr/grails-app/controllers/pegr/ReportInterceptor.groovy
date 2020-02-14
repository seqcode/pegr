package pegr

class ReportInterceptor {

    def springSecurityService
    def projectService
    
    /**
     * This is executed before the following actions in report controller.
     * If the report is automatically generated, admin or member or the porject user can view it;
     * else only the admin or the report user can view it.
     */
    boolean before() { 
        if (actionName == 'show') {
            def user = springSecurityService.currentUser
            if (user.isAdmin()) {
                return true
            } 
            def reportId = params.long('id')
            def report = SummaryReport.get(reportId)
            switch (report.type) {
                case ReportType.AUTOMATED:
                    def projectId = report.cohort?.project?.id
                    if (projectService.projectViewAuth(projectId)) {
                        return true                    
                    } else {
                        render(view: '/login/denied')
                        return false
                    }
                    break
                default:
                    if (report.user == user) {
                        return true                    
                    } else {
                        render(view: '/login/denied')
                        return false
                    }
                    break
            }                
        } else {
            return true
        }
    }
    
    boolean after() { true }

    void afterView() {
        // no-op
    }
}
