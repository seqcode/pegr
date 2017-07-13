package pegr
import grails.converters.*

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
                if (qcSettings.yeast) {
                    headers["yeast"] = []
                    subheaders["yeast"] = []
                    qcSettings.yeast.each { setting ->
                        if (setting.group) {
                            subheaders.yeast.push(setting.name)
                            if (!priorGroup || setting.group != priorGroup) {
                                headers.yeast.push([name: setting.group, rowspan: 1, colspan: 1])
                                priorGroup = setting.group
                            } else {
                                headers.yeast.last().colspan++
                            }
                        } else {
                            headers.yeast.push([name: setting.name, rowspan: 2, colspan: 1])
                        }
                    }
                }


                 if (qcSettings.yep) {
                    headers["yep"] = []
                    subheaders["yep"] = []
                    qcSettings.yep.each { setting ->
                        if (setting.group) {
                            subheaders.yep.push(setting.name)
                            if (!priorGroup || setting.group != priorGroup) {
                                headers.yep.push([name: setting.group, rowspan: 1, colspan: 1])
                                priorGroup = setting.group
                            } else {
                                headers.yep.last().colspan++
                            }
                        } else {
                            headers.yep.push([name: setting.name, rowspan: 2, colspan: 1])
                        }
                    }
                }




                [runStatus: runStatus.results, 
                 noResultSamples: runStatus.noResultSamples, 
                 qcSettings: qcSettings,
                 run: run,
                 headers: headers,
                 subheaders: subheaders
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
    
    def deleteAlignment(Long alignmentId, Long runId) {
        try {
            reportService.deleteAlignment(alignmentId)
            flash.message = "Success deleting the alignment!"
        } catch (ReportException e) {
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
    
    def fetchMemERDataAjax(String url) {
        def results = reportService.fetchMemERMotif(url) as JSON
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
        // get the purge alignments configs for the last time
        def purgeConfigStr = Chores.findByName(reportService.PURGE_ALIGNMENTS_CONFIG)?.value
        def purgeConfig = utilityService.parseJson(purgeConfigStr)
        [qcSettings: results.qcSettings, meta: results.meta, purgeConfig: purgeConfig]        
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
    
    def deletePurgedAlignments() {
        def message
        try {
            def startDate = params.startDate
            def endDate = params.endDate
            reportService.deletePurgedAlignments(startDate, endDate)
            message = "Sucess deleting purged alignments between ${startDate} and ${endDate}!"
        } catch (ReportException e) {
            message = e.message
        }
        def purgeConfigStr = Chores.findByName(reportService.PURGE_ALIGNMENTS_CONFIG)?.value
        def purgeConfig = utilityService.parseJson(purgeConfigStr)
        render(template: "purgeAlignments", model: [purgeConfig:purgeConfig, message: message])
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
    Integer alignmentCount
    String note
    String recommend
    String limits
    String user
    Date date
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
    String bamRaw
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
    String scidx
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
    List alignmentStatusList
    //List parameterNameList
}