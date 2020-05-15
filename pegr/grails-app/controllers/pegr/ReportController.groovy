package pegr
import grails.converters.*
import grails.util.Holders
import groovy.json.JsonSlurper

class ReportController {

    def springSecurityService
    def reportService
    def utilityService

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

    def runStatus(Long runId) {
        def run = SequenceRun.get(runId)
        if (!run) {
            flash.message = "Run not found!"
            redirect(controller: "sequenceRun", action: "index")
        } else {
            try {
                def runStatus = reportService.fetchRunStatus(run)
                def qcSettings = reportService.getQcSettings()?.qcSettings
                def headers = [:]
                def subheaders = [:]
                def priorGroup
		        def admin = false
                def user = springSecurityService.currentUser
                if (user.isAdmin()) {
                    admin = true
                }
                
                [runStatus: runStatus.results,
                 noResultSamples: runStatus.noResultSamples,
                 qcSettings: qcSettings,
                 run: run,
                 headers: headers,
                 subheaders: subheaders,
         		 isAdmin: admin
                ]
            } catch (ReportException e) {
                flash.message = e.message
                redirect(controller: "sequenceRun", action: "index")
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
    
	/** 
     * Delete selected (multiple) analysis workflow runs.
	 * This function is called in template report/_generalQc.gsp.
	 * It receives a list of analysis workflow run ids and run id in a json dictionary and iterates over the ids
	 * to send a request to reportService.deleteAnalysisWorkflowRun to delete each selected analysis workflow run. 
	 * Then, it redirects to another action that already exists.
     */
    def deleteAllAnalysisWorkflowRunAjax() {
		def listIds= JSON.parse(params.analysisRunIdsList)
		def runId= params.runId

		try {
			listIds.each{
			reportService.deleteAnalysisWorkflowRun(it.toLong())}
			flash.message = "Success deleting the analysis workflow run!"
		}
		catch(ReportException e) {
			flash.message = e.message
		}
		redirect(action: "runStatus", params: [runId: runId])
    }

    def show(Long id) {
        def report = SummaryReport.get(id)
        if (!report) {
            render(view: "/404")
            return
        }

        def currentProject = report.cohort?.project
        def projectUsers = ProjectUser.where { project == currentProject}.list()
        def imageMap = report.cohort?.imageMap
        [project: currentProject, projectUsers: projectUsers, report: report, imageMap: imageMap]
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

    def manage() {
        // get QC settings
        def results = reportService.getQcSettings()
        [qcSettings: results.qcSettings, meta: results.meta]
    }

    def saveQcSettings() {
        try {
            reportService.saveQcSettings(params)
            redirect(controller: "sequenceRun", action: "index")
        } catch (ReportException e) {
            flash.message = e.message
            redirect(action: "editQcSettings")
        }
    }

    def togglePreferredAnalysisWorkflowRun(Long analysisWorkflowRunId) {
        reportService.togglePreferredAnalysisWorkflowRun(analysisWorkflowRunId)
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

    def decisionTree(String type) {
        [type: type]
    }

    def getDecisionTreeAjax(String type) {
        def tree = reportService.getDecisionTree(type)
        def map = utilityService.parseJson(tree?.value)
        render (map as JSON)
        return
    }

    def saveDecisionTree(String json, String type) {
        try {
            reportService.saveDecisionTree(json, type)
        } catch (ReportException e) {
            flash.message = e.message
        }
        redirect(action: "decisionTree", params: [type: type])
    }

    def saveNotesAjax(Long cohortId, String notes) {
        reportService.saveNotes(cohortId, notes)
        render ""
        return
    }

    def updateAnalysisCodeAjax(Long analysisId, String code, String message) {
        reportService.updateAnalysisCode(analysisId, code, message)
        render ""
        return
    }

    def print(Long id) {
        def report = SummaryReport.get(id)
        if (!report) {
            render(view: "/404")
            return
        }
        def data = reportService.fetchDataForReport(id)
        def imageMap = report.cohort?.imageMap
        [report: report, imageMap: imageMap, sampleList: data]
    }

    def listFiles(Long id) {
        def samples = reportService.fetchDataForReport(id)
        [samples: samples]
    }
}


class SampleDTO {
    Long id
    String naturalId
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
    Integer analysisWorkflowRunCount
    String note
    String recommend
    List histories
}

class ExperimentDTO {
    Long id
    Long runId
    Long oldRunNum
    Long totalReads
    Long adapterDimerCount
    Map fastqc
    Map fastq
    List analysisWorkflowRuns
}

class RunStatusDTO {
    List steps
    List sampleStatusList
}

class SampleStatusDTO {
    Long sampleId
    String naturalId
    String target
    SequencingCohort cohort
    List analysisStatusList
}
