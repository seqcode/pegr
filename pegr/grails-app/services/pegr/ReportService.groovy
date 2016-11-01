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
    def alignmentStatsService
    def springSecurityService
    final Map QC_SETTINGS = ['general': "QC_SETTINGS", 'yeast': "YEAST_QC_SETTINGS"]
    final String PURGE_ALIGNMENTS_CONFIG = "PurgeAlignmentsConfig"
    final String GALAXY_CONFIG = "GalaxyConfig"
    final String DYNAMIC_ANALYSIS_STEPS = "DYNAMIC_ANALYSIS_STEPS"
    
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
            if (experiment.alignments.size() == 0) {
                noResultSamples << new SampleStatusDTO(sampleId: experiment.sample.id, 
                                                       cohort: experiment.cohort)
            }
            experiment.alignments.each { alignment ->
                def steps
                if (results.containsKey(alignment.pipeline)) {
                    steps = results[alignment.pipeline].steps
                } else {
                    try {
                        def jsonSlurper = new JsonSlurper()
                        steps = jsonSlurper.parseText(alignment.pipeline.steps)
                        results[alignment.pipeline] = new RunStatusDTO (steps: steps,
                                                                        sampleStatusList: [])
                    } catch (Exception e) {
                        throw new ReportException(message: "Pipeline steps are not properly defined!")
                    }
                }
                    
                // create a new alignment status                    
                def analysis = Analysis.findAllByAlignment(alignment) 
                def alignmentStatusDTO = getAlignmentStatusDTO(alignment, experiment, analysis)
                
                // iterate through the analysis and get each step's status  
                def motifCount = 0
                
                steps.eachWithIndex { step, index ->
                    // find the step's analysis by step
                    def stepAnalysisList = analysis.findAll {it.step == step[0]}
                    
                    // get the status for this step
                    alignmentStatusDTO.status[index] = getStepAnalysisStatus(stepAnalysisList)
                    
                    // compare the motif count
                    if (alignmentStatusDTO.status[index].code == "OK") {
                        switch (step[1]) {
                            case "meme":
                                motifCount = utilityService.queryJson(stepAnalysisList[0].statistics, "motifCount")
                                break
                            case "fimo":
                                def fimoCount = alignmentStatsService.queryDatasetsUriList(stepAnalysisList[0].datasets, "gff").size()
                                if (fimoCount != motifCount) {
                                    alignmentStatusDTO.status[index] = [code: "Error", error: "Motif missing"]
                                }
                                break
                            case "tagPileup":
                                if (stepAnalysisList.size() != motifCount) {
                                    alignmentStatusDTO.status[index] = [code: "Error", error: "Motif missing"]
                                }                         
                                break
                        }
                    }
                }

                // find the sample in that pipeline
                def sampleStatus = results[alignment.pipeline].sampleStatusList.find {it.sampleId == experiment.sample.id}
                if (!sampleStatus) {
                    sampleStatus = new SampleStatusDTO(sampleId: experiment.sample.id,
                                                       target: experiment.sample.target?.name,
                                                       cohort: experiment.cohort,
                                    alignmentStatusList: [alignmentStatusDTO])
                    results[alignment.pipeline].sampleStatusList << sampleStatus
                } else {    
                    sampleStatus.alignmentStatusList << alignmentStatusDTO
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
    * Get alignment status DTO from raw alignment and experiment
    * @param alignment the raw sequence alignment
    * @param experiment
    * @return alignment status DTO
    */
    def getAlignmentStatusDTO(SequenceAlignment alignment, SequencingExperiment experiment, def analysis) {
        def dto = [ 
                    alignmentId: alignment.id,
                    historyId: alignment.historyId,
                    genome: alignment.genome.name,
                    date: alignment.date,
                    status: [],
                    totalReads: experiment.totalReads,
                    requestedTags: experiment.sample.requestedTagNumber * 1000000,
                    adapterDimerPct: utilityService.divide(experiment.adapterDimerCount, experiment.totalReads),
                    mappedPct: utilityService.divide(alignment.mappedReads, experiment.totalReads),
                    uniquelyMappedPct: utilityService.divide(alignment.uniquelyMappedReads, experiment.totalReads),
                    deduplicatedPct: utilityService.divide(alignment.dedupUniquelyMappedReads, experiment.totalReads),
                    duplicationLevel: getDuplicationLevel(alignment.dedupUniquelyMappedReads, alignment.mappedReads),
                    isPreferred: alignment.isPreferred,            
                    dedupUniquelyMappedReads: alignment.dedupUniquelyMappedReads
                ]
        def stepsStr = Chores.findByName(DYNAMIC_ANALYSIS_STEPS)?.value
        def steps
        if (stepsStr) {
            steps = utilityService.parseJson(stepsStr)
        }
        if (steps && steps.size() > 0) {
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
    * delete a sequence alignment
    * @param alignmentId the ID of the alignment to be deleted
    */
    @Transactional
    def deleteAlignment(Long alignmentId) {
        try {
            ReportAlignments.executeUpdate("delete from ReportAlignments where alignment.id =:alignmentId", [alignmentId: alignmentId])

            Analysis.executeUpdate("delete from Analysis where alignment.id =:alignmentId", [alignmentId: alignmentId])
            
            SequenceAlignment.executeUpdate("delete from SequenceAlignment where id =:alignmentId", [alignmentId: alignmentId])
        } catch (Exception e) {
            log.error e
            throw new ReportException(message: "Error deleting the alignment!")
        } 
    }
    
   /**
    * Delete purged alignments with dates from saved chores
    * @param startDate
    * @param endDate
    */
    def deletePurgedAlignments(Date startDate, Date endDate) {
        final String RUN = "RUN"
        final String ERROR = "ERROR"
        
        // get Galaxy config
        def galaxyConfig = utilityService.parseJson(Chores.findByName(GALAXY_CONFIG)?.value)
        if (!galaxyConfig || !galaxyConfig.url || !galaxyConfig.key) {
            throw new ReportException(message: "Galaxy config is not correctly defined!")
        }
        
        // set current config in Chores
        def chore = Chores.findByName(PURGE_ALIGNMENTS_CONFIG)
        if (!chore) {
            chore = new Chores(name:PURGE_ALIGNMENTS_CONFIG)
        }
        
        def config = utilityService.parseJson(chore.value)
        if (config) {
            if (config.status == RUN) {
                throw new ReportException(message: "A job is currently running to delete purged alignments!")
            }
        } else {
            config = [:]
        }
        def runTime = new Date()
        config.currentRunTime = runTime
        config.currentStartDate = startDate
        config.currentEndDate = endDate
        config.status = RUN
        chore.value = JsonOutput.toJson(config)
        if (!chore.save(flush:true)) {
            throw new ReportException(message: "The job status cannot be updated. Job canceled!")
        }
        
        try {
            // find the alignments in the time interval
            def alignments = SequenceAlignment.where { date >= startDate && date <= endDate }.list()
            alignments.each { alignment ->
                def url = galaxyConfig.url + "api/histories/" + alignment.historyId + "?key=" + galaxyConfig.key
                def s = new URL(url).getText()
                def result = utilityService.parseJson(s)
                if (result?.purged) {
                    deleteAlignment(alignment.id)
                }
            }
            config = [lastStartDate: startDate,
                      lastEndDate: endDate,
                      lastRunTime: runTime
                     ]
        } catch (Exception e) {
            log.error e
            config.status = ERROR
        }
        // update config        
        chore.value = JsonOutput.toJson(config)
        if (!chore.save(flush:true)) {
            throw new ReportException(message: "Job finished successfully, but the job status cannot be updated.")
        }        
    }
    
   /**
    * Create summary reports for each project linked to the samples inside the 
    * sequence run. And link the preferred alignments to the corresponding summary reports. 
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
            experiment.alignments.each { alignment ->
                if (alignment.isPreferred) {
                    new ReportAlignments(report: report, alignment: alignment).save()
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
        ReportAlignments.executeUpdate("delete from ReportAlignments where report=:report", [report: report])
        report.delete()
    }
    
   /**
    * Fetch data for a given sample to be reported
    * @param sampleId sample's ID
    * @param preferredOnly whether to report preferred alignments only or report all alignments.
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
                experiment.alignments.each { alignment ->
                    if (!preferredOnly || alignment.isPreferred) {
                        def alignmentDTO = getAlignmentDTO(alignment)
                        updateAlignmentPct(alignmentDTO, expDTO)
                        expDTO.alignments << alignmentDTO
                        sampleDTO.alignmentCount++
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
    * @param preferredOnly whether to report preferred alignments only or report all alignments.
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
    * @param preferredOnly whether to report preferred alignments only or report all alignments.
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
            experiment.alignments.each { alignment ->
                if (!preferredOnly || alignment.isPreferred) {
                    def alignmentDTO = getAlignmentDTO(alignment)
                    updateAlignmentPct(alignmentDTO, expDTO)
                    expDTO.alignments << alignmentDTO
                    sampleDTO.alignmentCount++
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
        def alignments = ReportAlignments.where { report.id == reportId }.collect { it.alignment }

        def sampleDTOs = []
        alignments.each { alignment ->
            def alignmentDTO = getAlignmentDTO(alignment)
            def sample = alignment.sequencingExperiment.sample
            def sampleDTO = sampleDTOs.find{ it.id == sample.id}
            if (!sampleDTO) {
                sampleDTO = getSampleDTO(sample)
                sampleDTOs << sampleDTO
            } 
            sampleDTO.alignmentCount++
            
            def experiment = alignment.sequencingExperiment
            def experimentDTO = sampleDTO.experiments.find { it.id == experiment.id }
            if (!experimentDTO) {
                experimentDTO = getExperimentDTO(experiment)
                sampleDTO.experiments << experimentDTO
            }
            
            updateAlignmentPct(alignmentDTO, experimentDTO)
            
            experimentDTO.alignments << alignmentDTO
        }
        sampleDTOs.sort { it.id }
        return sampleDTOs
    }
    
   /**
    * Update sequence alignment's percentage type of statistics.
    * @param alignmentDTO alignmentDTO
    * @params experimentDTO experimentDTO
    */ 
    def updateAlignmentPct(AlignmentDTO alignmentDTO, ExperimentDTO experimentDTO) {
        alignmentDTO.mappedReadPct = utilityService.divide(alignmentDTO.mappedReads, experimentDTO.totalReads)
        alignmentDTO.uniquelyMappedPct = utilityService.divide(alignmentDTO.uniquelyMappedReads, experimentDTO.totalReads)
        alignmentDTO.deduplicatedPct = utilityService.divide(alignmentDTO.dedupUniquelyMappedReads, experimentDTO.totalReads)
    }
    
   /**
    * Get sampleDTO from raw sample
    * @param sample
    * @return sampleDTO
    */
    def getSampleDTO(Sample sample) {
        return new SampleDTO(id: sample.id,
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
          alignmentCount: 0,
          note: utilityService.queryJson(sample.note, "note")                             
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
                              alignments: []
                             )
    }
    
   /**
    * Get AlignmentDTO from raw alignment
    * @param alignment
    * @return alignmentDTO
    */
    def getAlignmentDTO(SequenceAlignment alignment) {
        def alignmentDTO = new AlignmentDTO(id: alignment.id,
                genome: alignment.genome,
                bam: alignment.bamFile,
                mappedReads: alignment.mappedReads,
                uniquelyMappedReads: alignment.uniquelyMappedReads,
                dedupUniquelyMappedReads: alignment.dedupUniquelyMappedReads,
                avgInsertSize: alignment.avgInsertSize,
                stdInsertSize: alignment.stdDevInsertSize,
                genomeCoverage: alignment.genomeCoverage,
                peHistogram: alignment.peHistogram,
                fourColor: [],
                composite: []
            )

        def statistics
        def parameter
        def analysisList = Analysis.findAllByAlignment(alignment)
        analysisList.each { analysis ->
            switch (analysis.category) {
                case "output_genetrack": // GeneTrack
                    def params = utilityService.queryJson(analysis.parameters, ["filter", "sigma", "exclusion"])
                    alignmentDTO.peakCallingParam = getPeakCallingParam(params.filter, params.exclusion, params.sigma)
                    break
                case "output_bedtoolsIntersect": // GeneTrack
                    def stats = utilityService.queryJson(analysis.statistics, ["numberOfPeaks", "singletons"])
                    alignmentDTO.peaks = stats.numberOfPeaks
                    alignmentDTO.singletons = stats.singletons
                    break
                case "output_cwpair2": // cwpair
                    alignmentDTO.peakPairs = utilityService.queryJson(analysis.statistics, "peakPairWis")
                    def params = utilityService.queryJson(analysis.parameters, ["up_distance", "down_distance", "binsize"])
                    alignmentDTO.peakPairsParam = getPeakPairsParam(params.up_distance, params.down_distance, params.binsize)
                    alignmentDTO.cwpairFile = alignmentStatsService.queryDatasetsUri(analysis.datasets, "gff")
                    break
                case "output_meme": // meme
                    alignmentDTO.memeFile = alignmentStatsService.queryDatasetsUri(analysis.datasets, "txt")
                    alignmentDTO.memeFig = alignmentStatsService.queryDatasetsUri(analysis.datasets, "html")
                    break
                case "output_fourColorPlot": // four color plot
                    alignmentDTO.fourColor = alignmentStatsService.queryDatasetsUriList(analysis.datasets, "png")
                    break
                case "output_tagPileup": //composite 
                    def motif = utilityService.queryJson(analysis.parameters, "input2X__identifier__")
                    def motifId = motif?.find( /\d+/ )?.toInteger()
                    def tabulars = alignmentStatsService.queryDatasetsUriList(analysis.datasets, "tabular")
                    if (tabulars && tabulars.size() > 0) {
                        if (motifId && motifId > 0){
                            alignmentDTO.composite[motifId-1] = tabulars.last()
                        }                      
                    }
                    break
            }
        }
        // in case not all composite figures have been received
        def compositeCount = alignmentDTO.composite.size()
        def fourColorCount = alignmentDTO.fourColor.size()
        if ( compositeCount < fourColorCount ) {
            alignmentDTO.composite[fourColorCount - 1] = null
        }
        alignmentDTO.nonPairedPeaks = getNonPairedPeaks(alignmentDTO.peaks, alignmentDTO.peakPairs)
        return alignmentDTO
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
    * Fetch MemER motifs from MemER file.
    * @param url of the MemER file
    * @return a list of memER motifs's information
    */
    def fetchMemERMotif(String url) {
        if (url == null || url == "") {
            throw new ReportException(message: "Missing URL!")
        }
        def pwm = []
        try {
            def config = utilityService.getGpfsConfig()
            
            def cmd = "ssh -i ${config.keyfile} ${config.username}@${config.host} cat ${url}"
            def timeout = 1000 * 60 * 1 // 1 min
            def data = utilityService.executeCommand(cmd, timeout)

            data.eachLine {
                def numbers = it.tokenize()
                def a = []
                numbers.eachWithIndex { n, index ->
                    if (index > 0 && index < 5) {
                        a.push(utilityService.getFloat(n))
                    }
                }
                pwm.push(a)
            }
        } catch (Exception e) {
            throw new ReportException(message: "Error fetching the memER data!")
        }
        return [pwm: pwm]
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
    * Toggle the "isPreferred" field of sequence alignment.
    * <p>
    * If the original value is false, set it to be true; otherwise, set to false.
    * @param alignmentId alignment's ID
    */
    @Transactional
    def togglePreferredAlignment(Long alignmentId) {
        def alignment = SequenceAlignment.get(alignmentId)
        alignment.isPreferred = !alignment.isPreferred
        alignment.save()
    }
    
   /**
    * Get the unknown index of a sequence run from Bcl2Fastq report.
    * <p>
    * First, try to get it from the local folder; if it's not there, fetch it from remote GPFS.
    * @param run sequence run
    * @return the unknown index file's path
    */
    def getUnknownIndex(SequenceRun run) {
        // First, try to get it from the local folder
        final String localFolder = "Bcl2FastqUnknownIndex"
        def localRoot = utilityService.getFilesRoot()
        File localPath = new File(localRoot, localFolder)
        if (!localPath.exists()) { 
            localPath.mkdirs() 
        } 
        def localFile = new File(localPath, "${run.directoryName}_unknownIndex.html")
        def filepath = localFile.getPath()

        // If the file is not in the local folder, fetch it from the remote GPFS.
        if (!localFile.exists()) {
            fetchUnknownIndexFromGpfs(run, filepath)
        }
        return filepath
    }
    
   /**
    * Fetch the unknown index file from GPFS using scp.
    * @param run the sequence run  
    * @param localFile the local filepath of the unknown index file
    */
    def fetchUnknownIndexFromGpfs(SequenceRun run, String localFile) {
        final String gpfsRoot = "/gpfs/cyberstar/pughhpc/galaxy-cegr/files/prep/prep_dir/"
        final String reportDir = "/Reports/html/"
        final String unknownIndexFile = "/default/unknown/unknown/lane.html"
        def config = utilityService.getGpfsConfig()

        String remotePath = gpfsRoot + run.directoryName + reportDir + run.fcId + unknownIndexFile
        
        def cmd = "scp -i ${config.keyfile} ${config.username}@${config.host}:${remotePath} ${localFile}"
        
        def timeout = 1000 * 60 * 1 // 1 min
        utilityService.executeCommand(cmd, timeout)
        
    }
}
