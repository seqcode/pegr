package pegr
import grails.converters.*

class ReportController {
    
    def springSecurityService
    def reportService
    
    def createReports(Long runId) {
        def run = SequenceRun.get(runId)
        if (!run) {
            render(view:"/404")
            return
        }
        try {
            reportService.createSummaryReportsForRun(run)
        } catch(ReportException e) {
            flash.message = e.message
        }
        redirect(action:"runStatus", params: [runId: runId])
    }
    
    /*
     * list the analysis status of all the runs
     * @param max max number of runs listed in a page
     * @param requestedStatus requested status of sequence run
     */
    def analysisStatus(Integer max, String requestedStatus) {
        if (requestedStatus == null || requestedStatus == "") {
            requestedStatus = RunStatus.RUN
        }
        params.max = Math.min(max ?: 15, 100)
        if (!params.sort) {
            params.sort = "date"
            params.order = "desc"
        }
        def runs = SequenceRun.where { status == requestedStatus }.list(params)
        [runs: runs, status: requestedStatus, totalCount: runs.totalCount]
    }
    
    def runStatus(Long runId) {
        def run = SequenceRun.get(runId)
        if (!run) {
            flash.message = "Run not found!"
            redirect(action: "analysisStatus")
        } else {
            try {
                def runStatus = reportService.fetchRunStatus(run)
                def reports = SummaryReport.findAllByRun(run)
                [runStatus: runStatus, run: run, reports: reports]
            } catch (ReportException e) {
                flash.message = e.message
                redirect(action: "analysisStatus")
            }       
        }
    }
    
    def deleteAlignment(Long alignmentId, Long runId) {
        try {
            reportService.deleteAlignment(alignmentId)
            flash.message = "Success deleting the alignment!"
        } catch (ReportException e) {
            flash.message = e.message
        }
        redirect(action: "runStatus", params: [runId: runId])
    }

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
        [project: currentProject, projectUsers: projectUsers, reportId: id]
    }
    
    def fetchDataForReportAjax(Long id) {
        def data = reportService.fetchDataForReport(id)
        render(template: 'details', model: [ sampleDTOs: data])        
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
    
    def fetchMemeDataAjax(String url) {
        def results = reportService.fetchMemeMotif(url) as JSON
        render results
    }
    
    def composite(String url) {
        [url: url]
    }
    
    def fetchCompositeDataAjax(String url) {
        def result
        try {
            result = reportService.fetchComposite(url)
            if (!result) {
                result = [error: "No composite data found!"] as JSON
            }
        } catch(ReportException e) {
            result = [error: e.message] as JSON
        } 
        render result
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
    Integer alignmentCount
}

class ExperimentDTO {
    Long id
    Long runId
    Long oldRunNum
    Long totalReads
    Long adapterDimerCount
    Map fastqc
    List alignments
}

class AlignmentDTO {
    Long id
    String genome
    
    Long mappedReads
    Long uniquelyMappedReads
    Long dedupUniquelyMappedReads
    
    Float mappedReadPct
    Float uniquelyMappedPct
    Float deduplicatedPct

    Integer avgInsertSize
    Float stdInsertSize
    Float genomeCoverage
    
    String peakCallingParam
    Long peaks
    Long singletons
    String peakPairsParam
    Long peakPairs
    Long nonPairedPeaks
    String memeFile
    String memeFig
    String peHistogram
    List fourColor
    List composite
}

class RunStatusDTO {
    List steps
    List sampleStatusList
}

class SampleStatusDTO {
    Long sampleId
    List alignmentStatusList
}

class AlignmentStatusDTO {
    String alignmentId
    String historyId
    String genome
    Date date
    List status
}