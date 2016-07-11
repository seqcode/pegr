package pegr

class ReportController {
    
    def springSecurityService
    def projectService
    def sampleService

    def index(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        def reports = SummaryReport.list(params)
        def totalCount = SummaryReport.count()
        [reports: reports, totalCount: totalCount]
    }
    
    def show(Long id) {
        def report = SummaryReport.get(id)
        if (!report) {
            render(view: "/404")
            return
        }
        def currentProject = report.project
        def projectUsers = ProjectUser.where { project == currentProject}.list()
        def alignments = SequenceAlignment.where { summaryReport.id == id }.list()
        def experiments = alignments.collect {it.sequencingExperiment}.unique()
        def samples = experiments.collect{it.sample}
        [project: currentProject, projectUsers: projectUsers, samples: samples, experiments: experiments, alignments: alignments]
    }
    
    def showProject(Long id) {
        def currentProject = Project.get(id)
        if (currentProject) {
            def projectUsers = ProjectUser.where { project==currentProject}.list()         
            def samples = ProjectSamples.where {project==currentProject}.list().collect{it.sample}
            def experiments = samples.collect{it.sequencingExperiments}.flatten()
            def alignments = experiments.collect{it.alignments}.flatten()
            render(view: "show", model: [project: currentProject, projectUsers: projectUsers, samples: samples, experiments: experiments, alignments: alignments])
        } else {
            flash.message = "Project not found!"
            redirect(action: "index")
        }
    }

    def showSamples() {
        def samples = []
        params.sampleIds.each{
            def sample = Sample.get(Long.parseLong(it))
            if (sample) {
                samples.push(sample)
            }
        }
        
    }
}

class DownstreamAnalysisCommand {
    String peakCallingParams
    Long numberOfPeaks
}