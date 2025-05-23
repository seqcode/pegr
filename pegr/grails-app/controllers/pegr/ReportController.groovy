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
	// axa677-180306: Added this function to handle the deletion of selected (multiple) alignments
	// This function is called in template report/_generalQc.gsp
	// it receives a list of alignment ids + run id in a json dictionary and iterates over the ids
	// to send a request to reportService.deleteAlignment(alignmentId) to delete each selected alignment
	// Then, it redirects to another action that already exists.
    def deleteAllAlignmentAjax() {
		def listAlignmentIds= JSON.parse(params.alignIdsList)
		def runId= params.runId
		//println listAlignmentIds
		//println runId
		try {
			listAlignmentIds.each{//println it
			reportService.deleteAlignment(it.toLong())}
			flash.message = "Success deleting the alignment!"
		}
		catch(ReportException e) {
			flash.message = e.message
		}
		redirect(action: "runStatus", params: [runId: runId])
    }

	// axa677-180306: No need for this function since we have written the previous one to handle deletion using checkboxes
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
        def report = SummaryReport.get(id)
        def imageMap = report.cohort?.imageMap
        
        def jsonSlurper = new JsonSlurper()
        def modules = jsonSlurper.parseText(report.pipeline?.reportModules)
        
        render(template: 'details', model: [ sampleDTOs: data, imageMap:imageMap, modules: modules])
    }

    def fetchMemeDataAjax(String url) {
        def result
        try {
            result = reportService.fetchMemeMotif(url) as JSON
        } catch(ReportException e) {
            result = [error: e.message] as JSON
        }
        render result
    }
    
    def fetchMemeFigAjax(String url) {
        def result = new URL(url).getText([connectTimeout: 6000, readTimeout: 2000]) 
            
        render result
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
    
    
    def peHistogram(String url) {
        [url: url]
    }
    
    def fetchPeHistogramDataAjax(String url) {
        def result
        try {
            result = reportService.fetchPeHistogram(url)
            if (!result) {
                result = [error: "No peHistogram data found!"] as JSON
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
                log.error "${e}"
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
        
        def jsonSlurper = new JsonSlurper()
        def modules = jsonSlurper.parseText(report.pipeline?.printModules)
        
        [report: report, imageMap: imageMap, sampleList: data, modules: modules]
    }

    def listFiles(Long id) {
        def samples = reportService.fetchDataForReport(id)
        [samples: samples, reportId: id]
    }
    
    def downloadScript() {
        def reportId = params.get("reportId")
        
        def samples
        if (reportId) {
            samples = reportService.fetchDataForReport(reportId.toLong())
        } else {
            def sampleIds = session.checkedSample
            samples = reportService.fetchDataForSamples(sampleIds)
        }
        
        def filetypes = params.list('filetypes') 
        
        def data = []
        
        samples.each { sample ->
            sample.experiments.each { experiment ->
                experiment.alignments.each { alignment ->
                    // TODO: hard-coded
                    def path
                    
                    if (filetypes.contains('fastq')) {
                        if (experiment.fastq.read1) {                        
                            // replace "preview=False" with "to_ext=fastqsanger.gz"
                            path = experiment.fastq.read1.replace("preview=False", "to_ext=fastqsanger.gz")

                            data.add("curl -o ${sample.id}_${sample.target}_${sample.antibody}_${sample.strain}_R1.fastq.gz ${path}") 
                        }

                        if (experiment.fastq.read2) {
                            // replace "preview=False" with "to_ext=fastqsanger.gz"
                            path = experiment.fastq.read2.replace("preview=False", "to_ext=fastqsanger.gz")

                            data.add("curl -o ${sample.id}_${sample.target}_${sample.antibody}_${sample.strain}_R2.fastq.gz ${path} ")  
                        }
                    }
                    
                    if (filetypes.contains('raw_bam')) {
                        if (alignment.bamRaw) {
                            data.add("curl -o ${sample.id}_${sample.target}_${sample.antibody}_${sample.strain}_raw.bam ${alignment.bamRaw}")  
                        }
                    }
                    
                    if (filetypes.contains('filtered_bam')) {
                        if (alignment.bam) {
                            data.add("curl -o ${sample.id}_${sample.target}_${sample.antibody}_${sample.strain}_filtered.bam ${alignment.bam} ")  
                        }
                    }
                    
                    if (filetypes.contains('bigwig')) {
                        if (alignment.bigwigForwardFile) {
                            // replace "preview=False" with "to_ext=bigwig"
                            path = alignment.bigwigForwardFile.replace("preview=False", "to_ext=bigwig")

                            data.add("curl -o ${sample.id}_${sample.target}_${sample.antibody}_${sample.strain}_forward.bigwig ${path}")  
                        }

                        if (alignment.bigwigReverseFile) {
                            // replace "preview=False" with "to_ext=bigwig"
                            path = alignment.bigwigReverseFile.replace("preview=False", "to_ext=bigwig")

                            data.add("curl -o ${sample.id}_${sample.target}_${sample.antibody}_${sample.strain}_reverse.bigwig ${path} ") 
                        }          
                    }
                }
            }
        }
        
        File file = File.createTempFile("download_script", ".sh")
        file.deleteOnExit()
        file.text= data.join(System.lineSeparator())
        
        try {
            response.setContentType("application/csv")
            response.setHeader("Content-disposition", "attachment;filename=\"download_script.sh\"")
            response.outputStream <<  file.getBytes()
            webRequest.renderView = false
        } catch(Exception e) {
            render "Error!"
        } finally {
            response.getOutputStream().flush()
            response.getOutputStream().close()
        }
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
    List histories
    List sampleModules
    List printModules
}

class ExperimentDTO {
    Long id
    Long runId
    String RunName
    Long totalReads
    Long adapterDimerCount
    Long totalReadsR2
    Long adapterDimerCountR2
    Map fastqc
    Map fastq
    List alignments
}

class AlignmentDTO {
    Long id
    String genome
    String bamRaw
    String bam
    String bigwigForwardFile
    String bigwigReverseFile
    Long mappedReads
    Long uniquelyMappedReads
    Long dedupUniquelyMappedReads
    Long mappedReadsR2
    Long uniquelyMappedReadsR2
    Long dedupUniquelyMappedReadsR2

    Float mappedReadPct
    Float uniquelyMappedPct
    Float deduplicatedPct
    Float mappedReadPct2
    Float uniquelyMappedPct2
    Float deduplicatedPct2

    Integer avgInsertSize
    Float stdDevInsertSize
    Float medianInsertSize
    Float modeInsertSize
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
    List memeSvgForward
    List memeSvgReverse
    String peHistogram
    List fourColor
    String scidx
    Integer readDbId
    
    Integer motifCount
    List composite
    List featureAnalysis
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
}
