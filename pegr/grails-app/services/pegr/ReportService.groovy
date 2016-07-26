package pegr

import grails.transaction.Transactional

class ReportException extends RuntimeException {
    String message
}

class ReportService {

    def utilityService
    
    /*
     * Create summary reports for each project linked to the samples inside the 
     * sequence run. And link the preferred alignments to the corresponding summary reports. 
     */
    @Transactional
    def createSummaryReportsForRun(SequenceRun run) {
        // create reports
        def reports = []
        run.experiments.each { experiment ->
            def projects = experiment.sample?.projects.sort { it.id }
            if (projects && projects.size() > 0) {
                def project = projects.first()
                def report = reports.find {it.project == project}
                if (!report) {
                    report = SummaryReport.findByRunAndProject(run, project)
                    if (!report) {
                        report = new SummaryReport(run: run, project: project)
                        report.save()
                    }
                    reports << report
                }
                experiment.alignments.each { alignment ->
                    if (alignment.isPreferred) {
                        new ReportAlignments(report: report, alignment: alignment).save()
                    }
                } 
            } else {
                throw new SequenceRunException(message: "Sample ${experiment.sample?.id} is not linked to a project yet!")
            }
        }        
    }
    
    def fetchData(Long reportId) {
        def alignments = ReportAlignments.where { report.id == reportId }.collect { it.alignment }
        def sampleDTOs = []
        alignments.each { alignment ->
            def alignmentDTO = new AlignmentDTO(id: alignment.id,
                                                genome: alignment.genome,
                                                mappedReads: alignment.mappedReads,
                                                uniquelyMappedReads: alignment.uniquelyMappedReads,
                                                dedupUniquelyMappedReads: alignment.dedupUniquelyMappedReads,
                                                avgInsertSize: alignment.avgInsertSize,
                                                stdInsertSize: alignment.stdDevInsertSize,
                                                genomeCoverage: alignment.genomeCoverage
                            )

            def statistics
            def parameter
            def analysisList = Analysis.findAllByAlignment(alignment)
            analysisList.each { analysis ->
                switch (analysis.category) {
                    // TODO: change the category name
                    case "testSeven": // GeneTrack
                        def stats = utilityService.queryJson(analysis.statistics, ["numberOfPeaks", "singletons"])
                        alignmentDTO.peaks = stats.numberOfPeaks
                        alignmentDTO.singletons = stats.singletons
                        def params = utilityService.queryJson(analysis.parameters, ["filter", "sigma", "exclusion"])
                        alignmentDTO.peakCallingParam = getPeakCallingParam(params.filter, params.exclusion, params.sigma)
                        break
                    case "testThree": // cwpair
                        def stats = utilityService.queryJson(analysis.statistics, ["peakPairWis"])
                        alignmentDTO.peakPairs = stats.peakPairWis
                        def params = utilityService.queryJson(analysis.parameters, ["up_distance", "down_distance", "binsize"])
                        alignmentDTO.peakPairsParam = getPeakPairsParam(params.up_distance, params.down_distance, params.binsize)
                        break
                    case "testNine": // meme
                        def datasets = utilityService.queryJson(analysis.datasets, ["txt"])
                        alignmentDTO.memeFile = datasets.txt
                }
            }
            
            alignmentDTO.nonPairedPeaks = getNonPairedPeaks(alignmentDTO.peaks, alignmentDTO.peakPairs)
            
            def sample = alignment.sequencingExperiment.sample
            def sampleDTO = sampleDTOs.find{ it.id == sample.id}
            if (!sampleDTO) {
                sampleDTO = new SampleDTO(id: sample.id,
                                          target: sample.target?.name,
                                          nTermTag: sample.target?.nTermTag,
                                          cTermTag: sample.target?.cTermTag,
                                          antibody: sample.antibody?.catalogNumber,
                                          strain: sample.cellSource?.strain?.name,
                                          geneticModification: sample.cellSource?.strain?.geneticModification,
                                          growthMedia: sample.growthMedia?.name,
                                          treatments: sample.treatments.join(", "),
                                          assay: sample.assay?.name,
                                          experiments: [],
                                          alignmentCount: 0
                                         )
                sampleDTOs << sampleDTO
            } 
            sampleDTO.alignmentCount++
            
            def experiment = alignment.sequencingExperiment
            def experimentDTO = sampleDTO.experiments.find { it.id == experiment.id }
            if (!experimentDTO) {
                experimentDTO = new ExperimentDTO(id: experiment.id,
                                                  runId: experiment.sequenceRun?.id,
                                                  oldRunNum: experiment.sequenceRun?.runNum,
                                                  totalReads: experiment.totalReads,
                                                  adapterDimerCount: experiment.adapterDimerCount,
                                                  alignments: []
                                                 )
                sampleDTO.experiments << experimentDTO
            }
            
            alignmentDTO.mappedReadPct = utilityService.divide(alignmentDTO.mappedReads, experimentDTO.totalReads)
            alignmentDTO.uniquelyMappedPct = utilityService.divide(alignmentDTO.uniquelyMappedReads, experimentDTO.totalReads)
            alignmentDTO.deduplicatedPct = utilityService.divide(alignmentDTO.dedupUniquelyMappedReads, experimentDTO.totalReads)
            
            experimentDTO.alignments << alignmentDTO
        }
        return sampleDTOs
    }
    
    // 
    def fetchMemeMotifAjax(String url) {
        def data = new URL(url).getText()
        def inBlock = false
        def count = 0
        def len, nsites, evalue
        def pwn
        data.eachLine {
            if (inBlock) {
                if (it.startsWith("----------------")) {
                    results.push([db: 0, 
                            id: count, 
                            alt: "MEME", 
                            len: len, 
                            nsites: nsites, 
                            evalue: evalue, 
                            pwn: pwn])
                    inBlock = false
                } else {
                    def numbers = it.tokenize()
                    def a = []
                    numbers.each { n ->
                        a.push(utilityService.getFloat(n))
                    }
                    pwn.push(a)
                }
            } else {
                if (it.startsWith("letter-probability matrix")) {
                    // digest the statistics
                    len = findInMotif(it, "w")
                    nsites = findInMotif(it, "nsites")
                    evalue = findInMotif(it, "evalue")
                    pwn = []
                    count++
                    inBlock = true
                }
            }

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
        def value = s[valueStart..valueEnd]
        return value
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
}
