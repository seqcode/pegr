package pegr
import groovy.json.*
import grails.transaction.Transactional

class ReportException extends RuntimeException {
    String message
}

class ReportService {

    def utilityService
    def alignmentStatsService
    def springSecurityService
    
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
                def alignmentStatusDTO = new AlignmentStatusDTO( 
                    alignmentId: alignment.id,
                    historyId: alignment.historyId,
                    genome: alignment.genome.name,
                    date: alignment.date,
                    status: [])
                
                // iterate through the analysis
                def analysis = Analysis.findAllByAlignment(alignment)   
                steps.eachWithIndex { step, index ->
                    if (analysis.find {it.stepId == step[0]}) {
                        alignmentStatusDTO.status[index] = true
                    } else {
                        alignmentStatusDTO.status[index] = false
                    }                  
                }

                // find the sample in that pipeline
                def sampleStatus = results[alignment.pipeline].sampleStatusList.find {it.sampleId == experiment.sample.id}
                if (!sampleStatus) {
                    sampleStatus = new SampleStatusDTO(sampleId: experiment.sample.id,
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
    
    /*
     * Create summary reports for each project linked to the samples inside the 
     * sequence run. And link the preferred alignments to the corresponding summary reports. 
     */
    @Transactional
    def createReportForCohort(SequencingCohort cohort) {
        if (cohort.report) {
            throw new ReportException(message: "A report has already been created for this cohort!")
        }
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
    
    
    def fetchDataForSample(Long sampleId) {
        def sampleDTOs = []
        def sample = Sample.get(sampleId)
        if (sample) {
            def sampleDTO = getSampleDTO(sample)
            sample.sequencingExperiments.each { experiment ->
                def expDTO = getExperimentDTO(experiment)
                experiment.alignments.each { alignment ->
                    def alignmentDTO = getAlignmentDTO(alignment)
                    updateAlignmentPct(alignmentDTO, expDTO)
                    expDTO.alignments << alignmentDTO
                    sampleDTO.alignmentCount++
                }
                sampleDTO.experiments << expDTO
            }
            sampleDTOs << sampleDTO
        }
        return sampleDTOs
    }
    
    def fetchDataForSamples(List sampleIds) {
        def sampleList = []
        if (sampleIds) {
            sampleIds.each { id ->
                def data = fetchDataForSample(id)
                if (data && data.size()) {
                    sampleList << data.first()
                }
            }
        }
        return sampleList
    }
    
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
    
    def updateAlignmentPct(AlignmentDTO alignmentDTO, ExperimentDTO experimentDTO) {
        alignmentDTO.mappedReadPct = utilityService.divide(alignmentDTO.mappedReads, experimentDTO.totalReads)
        alignmentDTO.uniquelyMappedPct = utilityService.divide(alignmentDTO.uniquelyMappedReads, experimentDTO.totalReads)
        alignmentDTO.deduplicatedPct = utilityService.divide(alignmentDTO.dedupUniquelyMappedReads, experimentDTO.totalReads)
    }
    
    
    def getSampleDTO(Sample sample) {
        return new SampleDTO(id: sample.id,
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
          alignmentCount: 0
         )
    }
    
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
    
    // 
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
    
    def getPeakCallingParam(def filter, def exclusion, def sigma) {
        def result = ""
        result += getParam("S", sigma)
        result += getParam("e", exclusion)
        result += getParam("F", filter)
        return result
    }
    
    def getPeakPairsParam(def upDistance, def downDistance, def binSize) {
        def result = ""
        result += getParam("u", upDistance)
        result += getParam("d", downDistance)
        result += getParam("b", binSize)
        return result
    }
    
    def getNonPairedPeaks(Long peaks, Long peakPairs) {
        def result
        if (peaks != null && peakPairs != null) {
            result = peaks - 2 * peakPairs
        }
        return result
    }
    
    def getParam(String shortName, String value) {
        def result = shortName
        if (value) {
            result += value.replace("\"", "")
        } else {
            result += "-"
        }        
        return result
    }
    
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
}
