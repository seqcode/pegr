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
    def save(StatsRegistrationCommand data, String apiUser) {
        def pipeline = getPipeline(apiUser)
        
        // check required fields
        def requiredFields = ["run", "sample", "genome", "toolId", "workflowId", "historyId", "toolCategory", "workflowStepId"]
        requiredFields.each { field ->
            if (!data.properties[field]) {
                throw new AlignmentStatsException(message: "Missing ${field}!")
            }
        }

        def experiment = SequencingExperiment.where {sequenceRun.id == data.run && sample.id == data.sample}.find()
        if (!experiment) {
            throw new AlignmentStatsException(message: "Sample ${data.sample} is not found in Run ${data.run}!")
        }
        def genome = Genome.findByName(data.genome)
        if (!genome) {
            throw new AlignmentStatsException(message: "Genome ${data.genome} not found!")
        }           
        def theAlignment = SequenceAlignment.findBySequencingExperimentAndGenomeAndPipelineAndHistoryId(experiment, genome, pipeline, data.historyId) 
        if (!theAlignment) {
            theAlignment = new SequenceAlignment(sequencingExperiment: experiment, 
                                                 genome: genome, 
                                                 pipeline: pipeline,
                                                 workflowId: data.workflowId,
                                                 historyId: data.historyId,
                                                 isPreferred: true, 
                                                 date: new Date())
            try {
                theAlignment.save(flush:true, failOnError: true)
            } catch (Exception e) {
                theAlignment = SequenceAlignment.findBySequencingExperimentAndGenomeAndPipelineAndHistoryId(experiment, genome, pipeline, data.historyId) 
                if (!theAlignment) {
                    throw new AlignmentStatsException("Error creating a new sequence alignment!")
                }
            }
        } 

        // save the data
        def statisticsStr = data.statistics ? JsonOutput.toJson(data.statistics) : null
        def parameterStr = data.parameters ? JsonOutput.toJson(data.parameters) : null
        def datasetsStr = data.datasets ? JsonOutput.toJson(data.datasets) : null
        
        def analysis = findOldAnalysis(data, theAlignment)
        if (analysis) {
            analysis.with {
                user = data.userEmail
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
                                    user: data.userEmail,
                                    parameters: parameterStr,
                                    statistics: statisticsStr,
                                    datasets: datasetsStr,
                                    date: new Date()
                                   )
        }
            
        if (!analysis.save()) {
            log.error analysis.errors
            throw new AlignmentStatsException(message: "Error saving the analysis ${analysis}!")
        }

        // store named fields
        if (data.statistics) {
            def updatedInAlignment = copyProperties(data.statistics, theAlignment)
            if (updatedInAlignment > 0) {
                theAlignment.date = new Date()
                if (!theAlignment.save()) {
                    log.error "Error saving ${updatedInAlignment} in Alignment!"
                }
            } 
            def updatedInExperiment = copyProperties(data.statistics, experiment)
            if (updatedInExperiment > 0) {
                if (!experiment.save()) {
                    log.error "Error saving ${updatedInExperiment} in Experiment!"
                }
            } 
        }

        return        
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
    
    def getPipeline(String apiUser) {
        def pipeline = Pipeline.where {name == apiUser}.order('pipelineVersion', 'desc').get(max: 1)
        return pipeline
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
    
    def queryDatasetsUri(String datasets, String type) {
        def result = queryDatasetsUriList(datasets, type)
        if (result && result.size() > 0) {
            return result[0]
        } else {
            return null
        }
    } 
    
    def queryDatasetsUriList(String datasets, String type) {
        def jsonList
        try {
            def jsonSlurper = new JsonSlurper()
            jsonList = jsonSlurper.parseText(datasets)
        } catch(Exception e) {   
        }
        if (jsonList) {
            def data = jsonList.findAll { d -> d.type == type }.collect { it.uri }
            return data.toList()
        }
        return []
    } 
    
    def queryDatasetsUriWithRead(String datasets, String statistics, String type) {
        def readNum = utilityService.queryJson(statistics, "read")
        def data = queryDatasetsUri(datasets, type)
        return [read: "read${readNum}", data: data]
    }
}