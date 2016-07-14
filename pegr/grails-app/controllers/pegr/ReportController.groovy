package pegr

class ReportController {
    
    def springSecurityService
    def reportService

    def all(Integer max) {
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
        def data = reportService.fetchData(id)
        [project: currentProject, projectUsers: projectUsers, sampleDTOs: data]
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


class SampleDTO {
    Long id
    String target
    String nTermTag
    String cTermTag
    String antibody
    String strain
    String geneticModification
    String growthMedia
    String treatments
    String assay
    List experiments
}

class ExperimentDTO {
    Long id
    Long runId
    Long oldRunNum
    List alignments
}

class AlignmentDTO {
    Long id
    String genome
    Integer readCount
    
    Integer mappedReadCount
    Integer uniquelyMappedCount
    Integer deduplicatedCount
    
    Double mappedReadPct
    Double uniquelyMappedPct
    Double deduplicatedPct
    
    Integer adapterDimerCount
    Integer avgInsertSize
    Double stdPE
    Double genomeCoverage
    
    String peakCallingParam
    Integer peaks
    Integer singletons
    String peakPairsParam
    Integer peakPairs
    Integer nonPairedPeaks
}