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
        // clean old reports
        def alignments = SequenceAlignment.where { summaryReport.run == run}. list()
        alignments.each {
            it.summaryReport = null
            it.save()
        }
        SummaryReport.executeUpdate("delete from SummaryReport where run.id = ?", [run.id])
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
                        alignment.summaryReport = report
                        alignment.save()
                    }
                } 
            } else {
                throw new SequenceRunException(message: "Sample ${experiment.sample?.id} is not linked to a project yet!")
            }
        }        
    }
    
    def fetchData(Long reportId) {
        def alignments = SequenceAlignment.where { summaryReport.id == reportId }.list()
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
                        def stats = utilityService.queryJson(analysis.statistics, ["numberOfPeaks"])
                        alignmentDTO.peaks = stats.numberOfPeaks
                        def params = utilityService.queryJson(analysis.parameters, ["filter", "sigma", "exclusion"])
                        alignmentDTO.peakCallingParam = getPeakCallingParam(params.filter, params.exclusion, params.sigma)
                        break
                    case "testThree": // cwpair
                        def stats = utilityService.queryJson(analysis.statistics, ["peakPairWis"])
                        alignmentDTO.peakPairs = stats.peakPairWis
                        def params = utilityService.queryJson(analysis.parameters, ["up_distance", "down_distance", "binsize"])
                        alignmentDTO.peakPairsParam = getPeakPairsParam(params.up_distance, params.down_distance, params.binsize)
                        break
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
                                          growthMedia: sample.cellSource?.growthMedia?.name,
                                          treatments: sample.cellSource?.treatments,
                                          assay: sample.assay?.name,
                                          experiments: []
                                         )
                sampleDTOs << sampleDTO
            }
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
    
    def getPeakCallingParam(def filter, def exclusion, def sigma) {
        def result = ""
        result += (sigma == null) ? "S-" : "S${sigma}"
        result += (exclusion == null) ? "e-" : "e${exclusion}"
        result += (filter == null) ? "F-" : "F${filter}"
        return result
    }
    
    def getPeakPairsParam(def upDistance, def downDistance, def binSize) {
        def result = ""
        result += (upDistance == null) ? "u-" : "u${upDistance}"
        result += (downDistance == null) ? "d-" : "d${downDistance}"
        result += (binSize == null) ? "b-" : "b${binSize}"
        return result
    }
    
    def getNonPairedPeaks(Long peaks, Long peakPairs) {
        def result
        if (peaks != null && peakPairs != null) {
            result = peaks - 2 * peakPairs
        }
        return result
    }
}
