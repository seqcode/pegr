package pegr
import groovy.sql.Sql

class SampleInterceptor {

    def springSecurityService
    def dataSource
    def sampleService
    
    /**
     * This is executed before the following actions in sample controller and checks authorization. 
     */
    boolean before() { 
        if (actionName == 'show') {
            // Only admin, member and users of projects that the sample belongs to can view the sample.
            def user = springSecurityService.currentUser
            if (user.isAdmin() || user.isMember()) {
                return true
            } else {
                def sampleId = params.long('id')
                def sql = new Sql(dataSource)
                def count = sql.rows("SELECT count(*) as cnt FROM project_user pu JOIN project_samples ps ON pu.project_id = ps.project_id WHERE pu.user_id = ${user.id} and ps.sample_id = ${sampleId}") 
                if (count[0].cnt > 0) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }                    
            }
        } else if (actionName == 'edit') {
            // Only admin and the owner and participant in the project which the sample belong to can edit the sample.
            def sample = Sample.get(params.long('sampleId'))
            if (sampleService.editAuth(sample)) {
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
