package pegr

SequenceRun.list().each { run ->
    run.experiments.each { experiment ->
        def projects = experiment.sample.projects
        if (projects.size() > 0) {
            def cohort = SequencingCohort.findByProjectAndRun(projects[0], run)
            if (!cohort) {
                cohort = new SequencingCohort(project: projects[0], run: run)
                cohort.save(flush: true, failOnError: true)
            }
            experiment.cohort = cohort
            experiment.save(failOnError: true)
        }
    }
    
}