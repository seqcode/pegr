package pegr
import groovy.json.*
import grails.transaction.Transactional

class ReportException extends RuntimeException {
    String message
}

/**
* A class to CRUD sequence run status and reports
*/
class ReportService {

    def grailsApplication
    def utilityService
    def analysisService
    def springSecurityService
    final Map QC_SETTINGS = ['general': "QC_SETTINGS"]
    final String DYNAMIC_ANALYSIS_STEPS = "DYNAMIC_ANALYSIS_STEPS"
    final String DECISION_TREE = "DECISION_TREE"
    
   /**
    * Fetch the status of the sequence run
    * @params run given sequence run
    * @return a map of results with key to be the pipeline and value to be the runStatusDTO,
    * and a list of samples with no results
    */
    def fetchRunStatus(SequenceRun run) {
        def results = [:] // key: pipeline, value: runStatusDTO
        def noResultSamples = []
        run.experiments.each { experiment ->
            if (experiment.analysisWorkflowRuns.size() == 0) {
                noResultSamples << new SampleStatusDTO(sampleId: experiment.sample.id, 
                                                       naturalId: experiment.sample.naturalId,
                                                       cohort: experiment.cohort)
            }
            experiment.analysisWorkflowRuns.each { analysisWorkflowRun ->
                def steps
                if (results.containsKey(analysisWorkflowRun.pipeline)) {
                    steps = results[analysisWorkflowRun.pipeline].steps
                } else {
                    try {
                        def jsonSlurper = new JsonSlurper()
                        steps = jsonSlurper.parseText(analysisWorkflowRun.pipeline.steps)
                        results[analysisWorkflowRun.pipeline] = new RunStatusDTO (steps: steps,
                                                                        sampleStatusList: [])
                    } catch (Exception e) {
                        throw new ReportException(message: "Pipeline steps are not properly defined!")
                    }
                }
                    
                // create a new analysisWorkflowRun status                    
                def analysisStatusDTO = getAnalysisStatusDTO(analysisWorkflowRun, experiment)
                
                // iterate through the analysis and get each step's status  
                def motifCount = 0
                
                steps.eachWithIndex { step, index ->
                    // find the step's analysis by step
                    def stepAnalysisList = analysis.findAll {it.step == step[0]}
                    
                    // get the status for this step
                    analysisStatusDTO.status[index] = getStepAnalysisStatus(stepAnalysisList)
                    
                    // compare the motif count
                    if (analysisStatusDTO.status[index].code == "OK") {
                        switch (step[1]) {
                            case "meme":
                                motifCount = utilityService.queryJson(stepAnalysisList[0].statistics, "motifCount")
                                break
                            case "fimo":
                                def fimoCount = analysisService.queryDatasetsUriList(stepAnalysisList[0].datasets, "gff").size()
                                if (fimoCount != motifCount) {
                                    analysisStatusDTO.status[index] = [code: "Error", error: "Motif missing"]
                                }
                                break
                            case "tagPileup":
                                if (stepAnalysisList.size() != motifCount) {
                                    analysisStatusDTO.status[index] = [code: "Error", error: "Motif missing"]
                                }                         
                                break
                        }
                    }
                }

                // find the sample in that pipeline
                def sampleStatus = results[analysisWorkflowRun.pipeline].sampleStatusList.find {it.sampleId == experiment.sample.id}
                if (!sampleStatus) {
                    sampleStatus = new SampleStatusDTO(sampleId: experiment.sample.id,
                                                       naturalId: experiment.sample.naturalId,
                                                       target: experiment.sample.target?.name,
                                                       cohort: experiment.cohort,
                                    analysisStatusList: [analysisStatusDTO])
                    results[analysisWorkflowRun.pipeline].sampleStatusList << sampleStatus
                } else {    
                    sampleStatus.analysisStatusList << analysisStatusDTO
                }
            }
        }

        return [results: results, noResultSamples: noResultSamples]
    }
    
   /** 
    * Get the step analysis' status
    * @param stepAnalysisList a list of analysis for a specific step
    * @return status map, including code, error(optional), and message(optional)
    */
    def getStepAnalysisStatus(List stepAnalysisList) {
        def result = [:]
        
        if (stepAnalysisList.size() == 0){
            result = [code: "NO"]
        } else {
            result.code = "OK"
            stepAnalysisList.each { stepAnalysis ->
                 result.analysisId = stepAnalysis.id
                // transform the status note
                def note = utilityService.parseJson(stepAnalysis.note)
                // if the note has not been processed
                if (!note) {
                    if (stepAnalysis.note) {
                        note = [code: "Error", error: stepAnalysis.note]
                    } else {
                        note = [code: "OK"]
                    }
                }
                // concatenate the note
                if ((result.code == "OK" && note.code != "OK") 
                    || (result.code == "Permission" && ["Zero", "Error"].contains(note.code))
                    || (result.code == "Zero" && note.code == "Error")) {
                    result.code = note.code
                }
                
                if (note.error) {
                    result.error = result.error ? result.error + " " + note.error : note.error
                }
                
                if (note.message) {
                    result.message = result.message ? result.message + " " + note.message : note.message
                }
            }
        }
        return result
    }
    
   /**
    * Get analysis status DTO from raw analysis and experiment
    * @param analysisWorkflowRun the raw analysis workflow run
    * @param experiment
    * @return analysis status DTO
    */
    def getAnalysisStatusDTO(AnalysisWorkflowRun analysisWorkflowRun, SequencingExperiment experiment) {
        def dto = [ 
                    analysisWorkflowRunId: analysisWorkflowRun.id,
                    historyId: analysisWorkflowRun.historyId,
                    historyUrl: analysisWorkflowRun.historyUrl,
                    date: analysisWorkflowRun.date,
                    isPreferred: analysisWorkflowRun.isPreferred,    
                    status: [],
                    genome: analysisWorkflowRun.genome.name,
                    totalReads: experiment.totalReads,
                    requestedTags: experiment.sample.requestedTagNumber * 1000000,
                    adapterDimerPct: utilityService.divide(experiment.adapterDimerCount, experiment.totalReads),
                    mappedPct: utilityService.divide(analysisWorkflowRun.mappedReads, experiment.totalReads),
                    uniquelyMappedPct: utilityService.divide(analysisWorkflowRun.uniquelyMappedReads, experiment.totalReads),
                    deduplicatedPct: utilityService.divide(analysisWorkflowRun.dedupUniquelyMappedReads, experiment.totalReads),
                    duplicationLevel: getDuplicationLevel(analysisWorkflowRun.dedupUniquelyMappedReads, analysisWorkflowRun.mappedReads),    
                    dedupUniquelyMappedReads: analysisWorkflowRun.dedupUniquelyMappedReads,
                    recommend: experiment.sample.recommend
                ]

        def stepsStr = Chores.findByName(DYNAMIC_ANALYSIS_STEPS)?.value
        def steps
        if (stepsStr) {
            steps = utilityService.parseJson(stepsStr)
        }
        if (steps && steps.size() > 0) {
            def analysis = Analysis.findAllByAnalysisWorkflowRun(analysisWorkflowRun)
            analysis.each {
                if (it.step in steps) {
                    def statistics = utilityService.parseJson(it.statistics)
                    if (statistics) {
                        statistics[0].each { key, value ->
                            if (value != null) {
                                dto[key] = value
                            }
                        }
                    }

                    def datasets = utilityService.parseJson(it.datasets)
                    if (datasets) {
                        dto[it.step] = datasets[0].uri
                    }
                }
            }
        }
        return dto
    }
    
    
   /**
    * get the Quality Control settings
    * @return a list of quality control criterias
    */
    def getQcSettings() {
        // get the QC settings
        def qcSettings = [:]
        def meta = [:]
        QC_SETTINGS.each { key, value ->
            qcSettings[key] = utilityService.parseJson(Chores.findByName(value)?.value)
            if (!qcSettings[key]) {
                qcSettings[key] = [[:]]
            }
            def metaName = value + "_META"
            meta[key] = utilityService.parseJson(Chores.findByName(metaName)?.value)
        }
        return [qcSettings:qcSettings, meta:meta]
    }
    
   /**
    * delete an analysis workflow run
    * @param analysisWorkflowRunId the ID of the analysis workflow run to be deleted
    */
    @Transactional
    def deleteAnalysisWorkflowRun(Long analysisWorkflowRunId) {
        try {
            ReportAnalysisWorkflowRuns.executeUpdate("delete from ReportAnalysisWorkflowRuns where analysisWorkflowRun.id =:analysisWorkflowRunId", [analysisWorkflowRunId: analysisWorkflowRunId])

            Analysis.executeUpdate("delete from Analysis where analysisWorkflowRun.id =:analysisWorkflowRunId", [analysisWorkflowRunId: analysisWorkflowRunId])
            
            AnalysisWorkflowRun.executeUpdate("delete from AnalysisWorkflowRun where id =:analysisWorkflowRunId", [analysisWorkflowRunId: analysisWorkflowRunId])
        } catch (Exception e) {
            log.error e
            throw new ReportException(message: "Error deleting the analysis workflow run!")
        } 
    }
    
   /**
    * Create summary reports for each project linked to the samples inside the 
    * sequence run. And link the preferred analysis workflow runs to the corresponding summary reports. 
    * @param cohort
    */
    @Transactional
    def createReportForCohort(SequencingCohort cohort) {
        def now = new Date()
        def user = springSecurityService.currentUser
        def report = new SummaryReport(name: cohort.toString(), status: ReportStatus.DRAFT, date: now, type: ReportType.AUTOMATED, user: user)
        if(!report.save()) {
            throw new ReportException(message: "Error saving the report!")
        }
        cohort.experiments.each { experiment ->
            experiment.analysisWorkflowRuns.each { analysisWorkflowRun ->
                if (analysisWorkflowRun.isPreferred) {
                    new ReportAnalysisWorkflowRuns(report: report, analysisWorkflowRun: analysisWorkflowRun).save()
                }
            } 
        }
        cohort.report = report
        cohort.save()
    }
    
   /**
    * Delete the given cohort's report
    * @param cohort
    */
    @Transactional
    def deleteReportForCohort(SequencingCohort cohort) {
        def report = cohort.report
        if (!report) {
            throw new ReportException(message: "No report found!")
        }
        cohort.report = null
        cohort.save()
        ReportAnalysisWorkflowRuns.executeUpdate("delete from ReportAnalysisWorkflowRuns where report=:report", [report: report])
        report.delete()
    }
    
   /**
    * Fetch data for a given sample to be reported
    * @param sampleId sample's ID
    * @param preferredOnly whether to report preferred analysisWorkflowRuns only or report all analysisWorkflowRuns.
    * Default to be false.
    * @return a list, containing only this sample's DTO if the sample exists, 
    * or nothing if the sample is not found.
    */
    def fetchDataForSample(Long sampleId, Boolean preferredOnly=false) {
        def sampleDTOs = []
        def sample = Sample.get(sampleId)
        if (sample) {
            def sampleDTO = getSampleDTO(sample)
            sample.sequencingExperiments.each { experiment ->
                def expDTO = getExperimentDTO(experiment)
                experiment.analysisWorkflowRuns.each { analysisWorkflowRun ->
                    if (!preferredOnly || analysisWorkflowRun.isPreferred) {
                        def analysisWorkflowRunDTO = getAnalysisWorkflowRunDTO(analysisWorkflowRun)
                        expDTO.analysisWorkflowRuns << analysisWorkflowRunDTO
                        sampleDTO.analysisWorkflowRunCount++
                        def analysisStatusDTO = getAnalysisStatusDTO(analysisWorkflowRun, experiment)
                        sampleDTO.histories << analysisStatusDTO.historyId
                    }
                }
                sampleDTO.experiments << expDTO
            }
            sampleDTOs << sampleDTO
        }
        return sampleDTOs
    }
    
   /**
    * Fetch data for a list of samples to be reported.
    * @param sampleIds a list of sample IDs
    * @param preferredOnly whether to report preferred analysis workflow runs only or report all analysis workflow runs.
    * Default to be false.
    * @return a list of sampleDTOs
    */
    def fetchDataForSamples(List sampleIds, Boolean preferredOnly=false) {
        def sampleList = []
        if (sampleIds) {
            sampleIds.each { id ->
                def data = fetchDataForSample(id, preferredOnly)
                if (data && data.size()) {
                    sampleList << data.first()
                }
            }
        }
        return sampleList
    }
    
   /**
    * Fetch data for a sequence run
    * @param runId sequence run ID
    * @param preferredOnly whether to report preferred analysis workflow runs only or report all analysis workflow runs.
    * Default to be false.
    * @return a list sampleDTOs
    */
    def fetchDataForRun(Long runId, Boolean preferredOnly=false) {
        if (!runId) {
            throw new ReportException(message: "Sequence run ID is missing!")
        }
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new ReportException(message: "Sequence run not found!")
        }
        def sampleDTOs = []
        run.experiments.each { experiment ->
            def sampleDTO = getSampleDTO(experiment.sample)
            def expDTO = getExperimentDTO(experiment)
            experiment.analysisWorkflowRuns.each { analysisWorkflowRun ->
                if (!preferredOnly || analysisWorkflowRun.isPreferred) {
                    def analysisWorkflowRunDTO = getAnalysisWorkflowRunDTO(AnalysisWorkflowRun)
                    expDTO.analysisWorkflowRuns << analysisWorkflowRunDTO
                    def analysisStatusDTO = getAnalysisStatusDTO(analysisWorkflowRun, experiment)
                    sampleDTO.histories << analysisStatusDTO.historyId 
                    sampleDTO.analysisWorkflowRunCount++
                }
            }
            sampleDTO.experiments << expDTO
            sampleDTOs << sampleDTO            
        }
        return sampleDTOs
    }
    
   /**
    * Fetch data for a report.
    * @param reportId report's ID
    * @return a list of sampleDTOs
    */
    def fetchDataForReport(Long reportId) {
        def analysisWorkflowRuns = ReportAnalysisWorkflowRuns.where { report.id == reportId }.collect { it.analysisWorkflowRun }

        def sampleDTOs = []
        analysisWorkflowRuns.each { analysisWorkflowRun ->            
            def sample = analysisWorkflowRun.sequencingExperiment.sample
            def sampleDTO = sampleDTOs.find{ it.id == sample.id}
            if (!sampleDTO) {
                sampleDTO = getSampleDTO(sample)
                sampleDTOs << sampleDTO
            } 
            sampleDTO.analysisWorkflowRunCount++
            
            def experiment = analysisWorkflowRun.sequencingExperiment
            def experimentDTO = sampleDTO.experiments.find { it.id == experiment.id }
            if (!experimentDTO) {
                experimentDTO = getExperimentDTO(experiment)
                sampleDTO.experiments << experimentDTO
            }
            def analysisWorkflowRunDTO = getAnalysisWorkflowRunDTO(analysisWorkflowRun)
            experimentDTO.analysisWorkflowRuns << analysisWorkflowRunDTO
        }
        sampleDTOs.sort { it.id }
        return sampleDTOs
    }
    
   /**
    * Get sampleDTO from raw sample
    * @param sample
    * @return sampleDTO
    */
    def getSampleDTO(Sample sample) {
        return new SampleDTO(id: sample.id,
          naturalId: sample.naturalId,
          source: sample.source,
          sourceId: sample.sourceId,
          target: sample.target?.name,
          nTermTag: sample.target?.nTermTag,
          cTermTag: sample.target?.cTermTag,
          antibody: sample.antibody?.catalogNumber,
          strain: sample.cellSource?.strain?.name,
          geneticModification: sample.cellSource?.strain?.geneticModification,
          growthMedia: sample.growthMedia?.name,
          treatments: sample.treatments*.name.join(", "),
          assay: sample.assay?.name,
          experiments: [],
          analysisWorkflowRunCount: 0,
          note: utilityService.queryJson(sample.note, "note"), 
          recommend: sample.recommend,
          histories: []
         )
    }
    
   /**
    * Get ExperimentDTO from raw experiment.
    * @params experiment
    * @return experimentDTO
    */
    def getExperimentDTO(SequencingExperiment experiment) {
        def fastqc = utilityService.parseJson(experiment.fastqcReport)
        def fastq = utilityService.parseJson(experiment.fastqFile)
        return new ExperimentDTO(id: experiment.id,
                              runId: experiment.sequenceRun?.id,
                              oldRunNum: experiment.sequenceRun?.runNum,
                              totalReads: experiment.totalReads,
                              adapterDimerCount: experiment.adapterDimerCount,
                              fastqc: fastqc,
                              fastq: fastq,
                              analysisWorkflowRuns: []
                             )
    }
    
   /**
    * Get analysisWorkflowRunDTO from raw analysisWorkflowRun
    * @param analysisWorkflowRun
    * @return analysisWorkflowRunDTO
    */
    def getAnalysisWorkflowRunDTO(AnalysisWorkflowRun analysisWorkflowRun) {
        // parse the results
        def analysisWorkflowRunDTO = utilityService.parseJson(analysisWorkflowRun.results)
        
        // derived values
        analysisWorkflowRunDTO.nonPairedPeaks = getNonPairedPeaks(analysisWorkflowRunDTO.peaks, analysisWorkflowRunDTO.peakPairs)
        analysisWorkflowRunDTO.mappedReadPct = utilityService.divide(analysisWorkflowRunDTO.mappedReads, analysisWorkflowRunDTO.totalReads)
        analysisWorkflowRunDTO.uniquelyMappedPct = utilityService.divide(analysisWorkflowRunDTO.uniquelyMappedReads, analysisWorkflowRunDTO.totalReads)
        analysisWorkflowRunDTO.deduplicatedPct = utilityService.divide(analysisWorkflowRunDTO.dedupUniquelyMappedReads, analysisWorkflowRunDTO.totalReads)
        
        // parse the parameters
        def parameters = utilityService.parseJson(analysisWorkflowRun.params)
        if (parameters) {
            analysisWorkflowRunDTO << parameters
        }
        
        // add additional information
        analysisWorkflowRunDTO["id"] = analysisWorkflowRun.id
        analysisWorkflowRunDTO["genome"] = analysisWorkflowRun.genome
        
        return analysisWorkflowRunDTO
    }
    
   /**
    * Fetch Meme motifs from Meme file.
    * @param url of the Meme file
    * @return a list of meme motifs's information
    */
    def fetchMemeMotif(String url) {
        def results = []
        if (url == null || url == "") {
            return results
        }
        try {
            def data = new URL(url).getText()
            def inBlock = false
            def count = 0
            def len, nsites, evalue
            def pwm

            data.eachLine {
                if (inBlock) {
                    if (it.startsWith("----------------")) {
                        results.push([db: 0, 
                                id: count, 
                                alt: "MEME", 
                                len: len, 
                                nsites: nsites, 
                                evalue: evalue, 
                                pwm: pwm])
                        inBlock = false
                    } else {
                        def numbers = it.tokenize()
                        def a = []
                        numbers.each { n ->
                            a.push(utilityService.getFloat(n))
                        }
                        pwm.push(a)
                    }
                } else {
                    if (it.startsWith("letter-probability matrix")) {
                        // digest the statistics
                        len = utilityService.getLong(findInMotif(it, "w"))
                        nsites = utilityService.getLong(findInMotif(it, "nsites"))
                        evalue = findInMotif(it, "E")
                        pwm = []
                        count++
                        inBlock = true
                    }
                }

            }
        } catch (Exception e) {
            throw new ReportException(message: "Error fetching the MEME data!")
        }
        return results
    }
    
   /**
    * Find the value of a given name in a string of motif information.
    * @param s the string of motif information
    * @param name the value's name
    * @return value
    */
    def findInMotif(String s, String name) {
        def nameStart = s.indexOf(name+"= ")
        if (nameStart < 0) {
            return null
        }        
        def valueStart = nameStart + name.length() + 2
        if (valueStart >= s.length()) {
            return null
        }
        def valueEnd = s.indexOf(" ", valueStart)
        def value = s[valueStart..valueEnd-1]
        return value
    }
    
   /**
    * Fetch composite from Composite file.
    * @param url of the Composite file
    * @return composite data
    */
    def fetchComposite(String url) {
        if (url == null || url == "") {
            return null
        }
        def results = []
        def data
        try {
            data = new URL(url).getText()
        } catch(Exception e) {
            throw new ReportException(message: "Error fetching the data!")
        }
        data.eachLine { line, lineNum ->
            def numbers = line.tokenize()
            if (lineNum == 0) {
                numbers.each { n ->
                    results.push([n])
                } 
            } else {
                numbers.eachWithIndex { n, c ->
                    if (c > 0) {
                        if (results[c-1] == null) {
                            throw new ReportException(message: "Error converting the data into array!")
                        }
                        results[c-1][lineNum] = n
                    }
                }
            }
        }
        String s = '[["Position", "Forward","Reverse"]'
        results.each {
            def a = it.join(",")
            s += ",[${a}]"
        }
        s += "]"
        return s
    }
    
   /**
    * Construct peak calling parameter string from values
    * @param filter value of filter
    * @param exclusion value of exclusion
    * @param sigma value of sigma
    * @return peak calling parameter string
    */
    def getPeakCallingParam(def filter, def exclusion, def sigma) {
        def result = ""
        result += getParam("S", sigma)
        result += getParam("e", exclusion)
        result += getParam("F", filter)
        return result
    }
    
   /**
    * Construct peak pairs parameter string from values
    * @param upDistance value of up distance
    * @param downDistance value of down distance
    * @param binSize value of bin size
    * @return peak pairs parameter string
    */
    def getPeakPairsParam(def upDistance, def downDistance, def binSize) {
        def result = ""
        result += getParam("u", upDistance)
        result += getParam("d", downDistance)
        result += getParam("b", binSize)
        return result
    }
    
   /**
    * Calculate the number of non-paired peaks
    * @param peaks number of peak
    * @param peakPairs number of peak pairs
    * @return number of non-paired peaks
    */
    def getNonPairedPeaks(Long peaks, Long peakPairs) {
        def result
        if (peaks != null && peakPairs != null) {
            result = peaks - 2 * peakPairs
        }
        return result
    }
    
   /**
    * Calculate the duplication level
    * @param dedup the number deduplicated uniquely mapped reads
    * @param mapped the number of mapped reads
    * @return duplication level
    */
    def getDuplicationLevel(Long dedup, Long mapped) {
        def dedupPct = utilityService.divide(dedup, mapped)
        def dupLevel = dedupPct ? 1.0 - dedupPct : null
        return dupLevel
    }
    
   /**
    * Construct the parameter string
    * @param shortName short name of the parameter
    * @param value value of the parameter
    * return the parameter string
    */
    def getParam(String shortName, String value) {
        def result = shortName
        if (value) {
            result += value.replace("\"", "")
        } else {
            result += "-"
        }        
        return result
    }
    
   /**
    * Update sequence run's status.
    * @param runId the sequence run's ID
    * @param statusStr the new status string
    */
    @Transactional
    def updateRunStatus(Long runId, String statusStr) {
        def run = SequenceRun.get(runId)
        if (!run) {
            throw new ReportException(message: "Sequence run not found!")
        }

        try {
            RunStatus status = statusStr as RunStatus
            run.status = status
            run.save()
        } catch(Exception e) {
            throw new ReportException(message: "Wrong run status!")
        }

    } 
    
   /**
    * Update report status.
    * @param reportId the report's ID
    * @param statusStr the new status string
    */
    @Transactional
    def updateReportStatus(Long reportId, String statusStr) {
        def report = SummaryReport.get(reportId)
        if (!report) {
            throw new ReportException(message: "Report not found!")
        }

        try {
            ReportStatus status = statusStr as ReportStatus
            report.status = status
            report.save()
        } catch(Exception e) {
            throw new ReportException(message: "Wrong report status!")
        }

    } 
    
   /**
    * Save the QC settings
    * @param params received by controller
    */
    @Transactional
    def saveQcSettings(def params) {
        def meta = Chores.findByName( QC_SETTINGS[params.type] + "_META")?.value
        if (!meta) {
            throw new ReportException(message: "Meta fields are not defined!")
        }
        def fields = utilityService.parseJson(meta)

        def lists = []
        
        fields.each { field ->
             lists.push(params.list(field))
        }
        def settings = []
        int count = lists[0].size()
        for (int i =0; i < count; ++i) {
            def setting = [:]
            fields.eachWithIndex { field, n ->
                if (lists[n][i] != null && lists[n][i] != "") {
                    switch(field) {
                        case ["min", "max", "reference_min_ratio", "reference_max_ratio"]:
                            setting[field] = utilityService.getFloat(lists[n][i])
                            break
                        default:
                            setting[field] = lists[n][i]
                            break
                    }                
                }
            }
            settings << setting
        }
        def name = QC_SETTINGS[params.type]
        def chores = Chores.findByName(name)
        if (!chores) {
            chores = new Chores(name: name)
        }
        chores.value = JsonOutput.toJson(settings)
        chores.save()
    }
    
   /**
    * Toggle the "isPreferred" field of analysis workflow run.
    * <p>
    * If the original value is false, set it to be true; otherwise, set to false.
    * @param analysisWorkflowRunId ID of the analysis workflow run
    */
    @Transactional
    def togglePreferredAnalysisWorkflowRun(Long analysisWorkflowRunId) {
        def analysisWorkflowRun = AnalysisWorkflowRun.get(analysisWorkflowRunId)
        analysisWorkflowRun.isPreferred = !analysisWorkflowRun.isPreferred
        analysisWorkflowRun.save()
    }
    
   /**
    * Get the unknown index of a sequence run from Bcl2Fastq report.
    * @param run sequence run
    * @return the unknown index file's path
    */
    def getUnknownIndex(SequenceRun run) {
        final String localFolder = "Bcl2FastqUnknownIndex"
        def localRoot = utilityService.getFilesRoot()
        File localPath = new File(localRoot, localFolder)
        if (!localPath.exists()) { 
            localPath.mkdirs() 
        } 
        def localFile = new File(localPath, "${run.directoryName}_unknownIndex.html")
        def filepath = localFile.getPath()

        return filepath
    }
    
    def getDecisionTree(String type) {
        def name = [DECISION_TREE, type].join("_")
        return Chores.findByName(name)
    }
    
    @Transactional
    def saveDecisionTree(String json, String type) {
        def chore = getDecisionTree(type)
        if (!chore) {
            chore = new Chores(name: [DECISION_TREE, type].join("_"))
        }
        def map = utilityService.parseJson(json)
        if (map) {            
            chore.value = JsonOutput.toJson(map)
            chore.save()
        } else {
            throw new ReportException(message: "Invalid json!")
        }
    }
    
    @Transactional
    def saveNotes(Long cohortId, String notes) {
        def cohort = SequencingCohort.get(cohortId)
        if (!cohort) {
            throw new ReportException(message: "Cohort not found!")
        }
        cohort.notes = notes
        cohort.save()
    }
    
    @Transactional
    def updateAnalysisCode(Long analysisId, String code, String message) {
        def analysis = Analysis.get(analysisId)
        if (!analysis) {
            throw new ReportException(message: "Analysis not found!")
        }
        def status = utilityService.parseJson(analysis.note)
        if (!status) {
            status = [:]
        }
        status.code = code
        status.message = message
        analysis.note = JsonOutput.toJson(status)
        analysis.save()
    }
}
