package pegr

class SequenceRunInterceptor {

    def SequenceRunService
    
    /** 
     * This is executed before the following actions in sequenceRun controller. 
     * Only when the status of the sequence run is not "COMPLETED" 
     * and only the admin or the sequence run user is allowed to make the changes.
     */
    boolean before() { 
        if (actionName in ['editRead',
                           'updateRead',
                           'editInfo',
                           'update',
                           'searchPool',
                           'addPool',
                           'removePool',
                           'addSamplesById',
                           'removeExperiment',
                           'updateSamples',
                           'run',
                           'fetchProjectsAjax',
                           'updateExperimentCohortAjax',
                           'addProject',
                           'removeProject',
                           'deleteSample',
                           'deleteProject']) {
            // get the sequence run
            def runId = params.long('runId')
            def run =  SequenceRun.get(runId)
            
            // check authorization
            def editable = sequenceRunService.checkEditable(run)
            if (!editable) {
                render(view: '/login/denied')
            }
            return editable
        } else if (actionName in ['uploadCohortImage',
                                 'removeCohortImageAjax']) {
            // get the sequence run
            def cohortId = params.long('cohortId')
            def run =  SequencingCohort.get(cohortId).run
            
            // check authorization
            def editable = sequenceRunService.checkEditable(run)
            if (!editable) {
                render(view: '/login/denied')
            }
            return editable
        } 
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
