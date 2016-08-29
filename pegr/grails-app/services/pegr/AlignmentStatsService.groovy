package pegr
import grails.transaction.Transactional
import grails.converters.*
import groovy.json.*

class AlignmentStatsException extends RuntimeException {
    String message
}

class AlignmentStatsService {
    def utilityService
    
    @Transactional
    def save(StatsRegistrationCommand data, User apiUser) {        
        // check required fields
        def requiredFields = ["run", "sample", "genome", "toolId", "workflowId", "historyId", "toolCategory", "workflowStepId"]
        requiredFields.each { field ->
            if (!data.properties[field]) {
                throw new AlignmentStatsException(message: "Missing ${field}!")
            }
        }
        
        // find the pipeline by user and workflowId
        def pipeline = Pipeline.findByWorkflowId(data.workflowId)
        
        if (!pipeline) {
            throw new AlignmentStatsException(message: "Pipeline not found for workflow ID ${data.workflowId}!")
        }
        
        // find the sequencing experiment by runId and sampleId
        def experiment = SequencingExperiment.where {sequenceRun.id == data.run && sample.id == data.sample}.find()
        if (!experiment) {
            throw new AlignmentStatsException(message: "Sample ${data.sample} is not found in Run ${data.run}!")
        }
        
        // find the genome
        def genome = Genome.findByName(data.genome)
        if (!genome) {
            throw new AlignmentStatsException(message: "Genome ${data.genome} not found!")
        }           
        
        // find the sequence alignment. if no match, create a new alignment.
        def theAlignment = SequenceAlignment.findBySequencingExperimentAndGenomeAndPipelineAndHistoryId(experiment, genome, pipeline, data.historyId) 
        if (!theAlignment) {
            theAlignment = new SequenceAlignment(sequencingExperiment: experiment, 
                                                 genome: genome, 
                                                 pipeline: pipeline,
                                                 historyId: data.historyId,
                                                 isPreferred: true, 
                                                 date: new Date())
            theAlignment.save(flush:true, failOnError: true)
        } 

        // convert statistics, parameter, datasets to string
        def statisticsStr = data.statistics ? JsonOutput.toJson(data.statistics) : null
        def parameterStr = data.parameters ? JsonOutput.toJson(data.parameters) : null
        def datasetsStr = data.datasets ? JsonOutput.toJson(data.datasets) : null
        
        // save analysis. If it's a re-run inside an old history, overwrite the old analysis; else create a new analysis.
        def analysis = findOldAnalysis(data, theAlignment)        
        if (analysis) {
            // throw an exception if a different user tries to overwrite the analysis
            if (analysis.user != apiUser) {
                throw new AlignmentStatsException(message: "Analysis cannot be overwritten by a different user!")
            }
            analysis.with {
                parameters = parameterStr
                statistics = statisticsStr
                datasets = datasetsStr
                date = new Date()
            }
        } else {
            analysis = new Analysis(alignment: theAlignment,
                                    tool: data.toolId,             
                                    category: data.toolCategory,
                                    stepId: data.workflowStepId,
                                    user: apiUser,
                                    parameters: parameterStr,
                                    statistics: statisticsStr,
                                    datasets: datasetsStr,
                                    date: new Date()
                                   )
        }
            
        analysis.save(failOnError: true)

        // store statistics and datasets to named fields
        saveStatistics(theAlignment, experiment, data.statistics)        
        saveDatasets(theAlignment, experiment, data.toolCategory, data.datasets, data.statistics)

        return        
    }
    
    def saveStatistics(SequenceAlignment alignment, SequencingExperiment experiment, List statistics) {
        if (statistics) {
            def updatedInAlignment = copyProperties(statistics, alignment)
            if (updatedInAlignment > 0) {
                alignment.date = new Date()
                alignment.save(failOnError: true)
            } 
            def updatedInExperiment = copyProperties(statistics, experiment)
            if (updatedInExperiment > 0) {
                experiment.save(failOnError: true)
            } 
        }
    }
    
    def saveDatasets(SequenceAlignment alignment, SequencingExperiment experiment, String category, List datasets, List statistics) {
        try {
            def jsonSlurper = new JsonSlurper()
            switch (category) {
                case ["output_fastqRead1", "output_fastqRead2"]: // fastq
                    def fastq = [:]
                    if (experiment.fastqFile) {
                        fastq = jsonSlurper.parseText(experiment.fastqFile)
                    }
                    def newFastq = queryDatasetsUri(datasets, "fastqsanger")
                    if (newFastq) {
                        def key = "read${category[-1]}"
                        fastq[key] = newFastq
                        experiment.fastqFile = JsonOutput.toJson(fastq)
                        experiment.save(faileOnError: true)
                    }                    
                    break
                case "output_fastqc": // fastqc report
                    def fastqc = [:]
                    if (experiment.fastqcReport) {
                        fastqc = jsonSlurper.parseText(experiment.fastqcReport)
                    }
                    def newFastqc = queryDatasetsUriWithRead(datasets, statistics, "html")
                    if (newFastqc) {
                        fastqc[newFastqc.read] = newFastqc.data
                        experiment.fastqcReport = JsonOutput.toJson(fastqc)
                        experiment.save(faileOnError: true)
                    }
                    break
                case "output_samtoolFilter": // bam
                    def newBam = queryDatasetsUri(datasets, "bam")
                    if (newBam) {
                        alignment.bamFile = newBam
                        alignment.save(failOnError: true)
                    }         
                    break
                case "output_peHistogram": //pe histogram
                    def newHistogram = queryDatasetsUri(datasets, "png")
                    if (newHistogram) {
                        alignment.peHistogram = newHistogram
                        alignment.save(failOnError: true)
                    }                    
                    break
            }
        } catch (Exception e) {
            log.error e
            throw new AlignmentStatsException(message: "Error saving the datasets!")
        }
    }
    
    def findOldAnalysis(StatsRegistrationCommand data, SequenceAlignment alignment) {
        def oldAnalysis = null
        switch (data.toolCategory) {
            case "output_tagPileup":
                // special handling of multip Tag Pileup calls
                def MOTIF_ID = "input2X__identifier__"
                def newMotifId = (data.parameters && data.parameters.containsKey(MOTIF_ID)) ? data.parameters[MOTIF_ID] : null 
                if (newMotifId) {
                    def oldAnalysisList = Analysis.findAllByStepIdAndAlignment(data.workflowStepId, alignment)
                    for (int i = 0; i< oldAnalysisList.size(); ++i) {
                        def oldMotifId = utilityService.queryJson(oldAnalysisList[i].parameters, MOTIF_ID)
                        if (oldMotifId && oldMotifId == newMotifId) {
                            oldAnalysis = oldAnalysisList[i]
                            break
                        }
                    }
                }
                break
            default:
                oldAnalysis = Analysis.findByStepIdAndAlignment(data.workflowStepId, alignment)
                break
        }
        return oldAnalysis
    }
    
    def copyProperties(source, target) {
        def updatedProperties = 0
        if (source instanceof List) {
            source.each {
                updatedProperties += copyMap(it, target)
            }
        } else {
            updatedProperties = copyMap(source, target)
        }
        return updatedProperties
    }
    
    def copyMap(source, target) {
        def updatedProperties = 0
        def readKey = source.containsKey("read") ? "read${source.read}" : "read"
        source.each { key, value ->
            if (key != "read" && target.hasProperty(key) && value != null) {
                // skip read 2's adapter dimer count
                if (!(key == "adapterDimerCount" && readKey == "read2")) {
                    try {
                        target[key] = value    
                    } catch(org.codehaus.groovy.runtime.typehandling.GroovyCastException e) {
                        log.error e
                        throw new AlignmentStatsException(message: e.message)
                    }                
                    updatedProperties++
                }
            }
        }
        return updatedProperties
    }

    def queryDatasetsUri(List jsonList, String type) {
        def data = jsonList?.find { d -> d.type == type }?.uri
        return data
    }
    
    
    def queryDatasetsUri(String datasets, String type) {
        def jsonList = utilityService.parseJson(datasets)
        def result = queryDatasetsUri(jsonList, type)
    } 

    def queryDatasetsUriList(List jsonList, String type) {
        def data = jsonList ? jsonList.findAll { d -> d.type == type }.collect { it.uri }.toList() : []
        return data
    }
    
    def queryDatasetsUriList(String datasets, String type) {
        def jsonList = utilityService.parseJson(datasets)
        def results = queryDatasetsUriList(jsonList, type)
        return results
    } 
    
    def queryDatasetsUriWithRead(List datasets, List statistics, String type) {
        def readNum 
        if (statistics) {
            statistics.each { map ->
                if (map.containsKey("read")) {
                    readNum = map["read"]
                }
            }
        }

        def data = queryDatasetsUri(datasets, type)
        if (data) {
            return [read: "read${readNum}", data: data]
        } else {
            return null
        }
    }
    
    def queryDatasetsUriWithRead(String datasets, String statistics, String type) {
        def readNum = utilityService.queryJson(statistics, "read")
        def data = queryDatasetsUri(datasets, type)
        if (data) {
            return [read: "read${readNum}", data: data]
        } else {
            return null
        }
    }
}