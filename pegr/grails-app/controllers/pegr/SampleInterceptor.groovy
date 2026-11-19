package pegr
import groovy.sql.Sql

class SampleInterceptor {

    def springSecurityService
    def dataSource
    def sampleService

    // Actions that read one sample, identified by params.id.
    static final List VIEW_ACTIONS = [
        'show',
        'fetchDataForSampleAjax'
    ]

    // Actions that view the edit pages of, or change, one sample, identified by params.sampleId.
    static final List EDIT_ACTIONS = [
        'edit',
        'editOther',
        'updateOther',
        'editProtocol',
        'updateProtocol',
        'editTarget',
        'updateTarget',
        'editAntibody',
        'updateAntibody',
        'searchAntibody',
        'previewAntibody',
        'addAntibodyToSample',
        'editCellSource',
        'updateCellSource',
        'searchCellSource',
        'previewCellSource',
        'addCellSourceToSample',
        'showItem',
        'saveBarcode',
        'updateAjax',
        'updateSampleStatusAjax'
    ]

    /**
     * This is executed before the following actions in sample controller and checks authorization.
     */
    boolean before() {
        if (actionName in VIEW_ACTIONS) {
            // Only admin, member and users of projects that the sample belongs to can view the sample.
            if (viewAuth(params.long('id'))) {
                return true
            }
            deny()
            return false
        } else if (actionName in EDIT_ACTIONS) {
            // Only admin and the owner and participant in the project which the sample belong to can edit the sample.
            def sample = Sample.get(params.long('sampleId'))
            if (sampleService.editAuth(sample)) {
                return true
            }
            deny()
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

    private boolean viewAuth(Long sampleId) {
        def user = springSecurityService.currentUser
        if (user.isAdmin() || user.isMember()) {
            return true
        }
        def sql = new Sql(dataSource)
        try {
            def count = sql.rows("SELECT count(*) as cnt FROM project_user pu JOIN project_samples ps ON pu.project_id = ps.project_id WHERE pu.user_id = ${user.id} and ps.sample_id = ${sampleId}")
            return count[0].cnt > 0
        } finally {
            sql.close()
        }
    }

    private void deny() {
        if (actionName.endsWith('Ajax')) {
            render(status: 403, text: "Not authorized!")
        } else {
            render(view: '/login/denied')
        }
    }
}
