package pegr
import grails.converters.*

class ReportController {
    
    def springSecurityService
    def reportService
    
    def createReportForCohortAjax(Long cohortId) {
        def cohort = SequencingCohort.get(cohortId)
        if (cohort && !cohort.report) {
            reportService.createReportForCohort(cohort)
        }
        render template: "/report/reportRow", model: [cohort: cohort]
    }
    
    def deleteReportForCohortAjax(Long cohortId) {
        def cohort = SequencingCohort.get(cohortId)
        reportService.deleteReportForCohort(cohort)
        render template: "/report/reportRow", model: [cohort: cohort]
    }
    
    /*
     * list the analysis status of all the runs
     * @param max max number of runs listed in a page
     * @param requestedStatus requested status of sequence run
     */
    def analysisStatus(Integer max, String requestedStatus) {
        if (requestedStatus == null || requestedStatus == "") {
            requestedStatus = RunStatus.ANALYZING
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
                def qcSettings = reportService.getQcSettings()
                [runStatus: runStatus.results, 
                 noResultSamples: runStatus.noResultSamples, 
                 qcSettings: qcSettings,
                 run: run]
            } catch (ReportException e) {
                flash.message = e.message
                redirect(action: "analysisStatus")
            }       
        }
    }
    
    def updateRunStatusAjax(Long runId, String status) {
        reportService.updateRunStatus(runId, status)
        render status 
    }
     
    def updateReportStatusAjax(Long reportId, String status) {
        reportService.updateReportStatus(reportId, status)
        render status 
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

    def automatedReportList(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        def query = SummaryReport.where { type == ReportType.AUTOMATED }
        def reports = query.list(params)
        def totalCount = query.count()
        [reports: reports, totalCount: totalCount]
    }
    
    def show(Long id) {
        def report = SummaryReport.get(id)
        if (!report) {
            render(view: "/404")
            return
        }

        def currentProject = report.cohort?.project
        def projectUsers = ProjectUser.where { project == currentProject}.list()
        [project: currentProject, projectUsers: projectUsers, report: report]
    }
    
    def fetchDataForReportAjax(Long id) {
        def data = reportService.fetchDataForReport(id)
        render(template: 'details', model: [ sampleDTOs: data])        
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
    
    def editQcSettings() {
        def qcSettings = reportService.getQcSettings()
        if (!qcSettings || qcSettings.size() == 0) {
            qcSettings = [[:]]
        }
        [qcSettings: qcSettings]
    }
    
    def saveQcSettings() {
        try {
            reportService.saveQcSettings(params)
            redirect(action: "analysisStatus")
        } catch (ReportException e) {
            flash.message = e.message
            redirect(action: "editQcSettings") 
        }
    }
    
    def togglePreferredAlignment(Long alignmentId) {
        reportService.togglePreferredAlignment(alignmentId)
        render ""
    }
    
    def unknownIndex(Long runId) {
        def run = SequenceRun.get(runId)
        if (!run) {
            render(view: "/404")
        } else {
            try {
                def file = reportService.getUnknownIndex(run)
                def htmlContent = new File(file).text
                render text: htmlContent, contentType:"text/html", encoding:"UTF-8"    
            } catch (Exception e) {
                render(view: "/404")
            }
        }
    }
}


class SampleDTO {
    Long id
    String source
    String sourceId
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
    Map fastq
    List alignments
}

class AlignmentDTO {
    Long id
    String genome
    String bam
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
    String cwpairFile
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
    String target
    SequencingCohort cohort
    List alignmentStatusList
}

class AlignmentStatusDTO {
    String alignmentId
    String historyId
    String genome
    Date date
    List status
    
    Long totalReads
    Long requestedTags
    Float adapterDimerPct
    Float mappedPct
    Float uniquelyMappedPct
    Float deduplicatedPct
    Float duplicationLevel
        
    Boolean isPreferred
}