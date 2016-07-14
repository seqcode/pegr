package pegr

import grails.transaction.Transactional

class ReportException extends RuntimeException {
    String message
}

class ReportService {

    /*
     * Create summary reports for each project linked to the samples inside the 
     * sequence run. And link the preferred alignments to the corresponding summary reports. 
     */
    @Transactional
    def createSummaryReportsForRun(SequenceRun run) {    
        // clean old reports
        def alignments = SequenceAlignment.where { summaryReport.run == run}. list()
        alignments.each {
            it.summaryReport = null
            it.save()
        }
        SummaryReport.executeUpdate("delete from SummaryReport where run.id = ?", [run.id])
        // create reports
        def reports = []
        run.experiments.each { experiment ->
            def projects = experiment.sample?.projects.sort { it.id }
            if (projects && projects.size() > 0) {
                def project = projects.first()
                def report = reports.find {it.project == project}
                if (!report) {
                    report = SummaryReport.findByRunAndProject(run, project)
                    if (!report) {
                        report = new SummaryReport(run: run, project: project)
                        report.save()
                    }
                    reports << report
                }
                experiment.alignments.each { alignment ->
                    if (alignment.isPreferred) {
                        alignment.summaryReport = report
                        alignment.save()
                    }
                } 
            } else {
                throw new SequenceRunException(message: "Sample ${experiment.sample?.id} is not linked to a project yet!")
            }
        }        
    }
    
    def fetchData(Long reportId) {
        def alignments = SequenceAlignment.where { summaryReport.id == reportId }.list()
        def sampleDTOs = []
        alignments.each { alignment ->
            def alignmentDTO = new AlignmentDTO(id: alignment.id,
                            genome: alignment.genome
                                                // TODO
                            )
            def analysis = Analysis.findAllByAlignment(alignment)
            // TODO
            def sample = alignment.sequencingExperiment.sample
            def sampleDTO = sampleDTOs.find{ it.id == sample.id}
            if (!sampleDTO) {
                sampleDTO = new SampleDTO(id: sample.id,
                                          experiments: []
                                         // TODO
                                         )
                sampleDTOs << sampleDTO
            }
            def experiment = alignment.sequencingExperiment
            def experimentDTO = sampleDTO.experiment.find { it.id == experiment.id }
            if (!experimentDTO) {
                experimentDTO = new ExperimentDTO(id: experiment.id,
                                                  alignments: []
                                                  // TODO
                                                 )
                sampleDTO.experiments << experimentDTO
            }
            experimentDTO << alignmentDTO
        }
        return sampleDTOs
    }
}
