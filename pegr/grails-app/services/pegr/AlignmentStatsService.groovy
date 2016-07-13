package pegr
import grails.transaction.Transactional
import grails.converters.*
import groovy.json.*

class AlignmentStatsException extends RuntimeException {
    String message
}

class AlignmentStatsService {
    
    @Transactional
    def save(StatsRegistrationCommand data, String apiUser) {
        def pipeline = getPipeline(apiUser)
        
        // check required fields
        def requiredFields = ["run", "sample", "genome", "toolId", "workflowId", "historyId", "toolCategory"]
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
        def alignment = SequenceAlignment.findBySequencingExperimentAndGenome(experiment, genome)
        if (!alignment) {
            throw new AlignmentStatsException(message: "Sequence Alignment for Run ${data.run}, Sample ${data.sample} and Genome ${data.genome} not found!")
        }
        // save the data
        def statisticsStr = data.statistics ? JsonOutput.toJson(data.statistics) : null
        def parameterStr = data.parameters ? JsonOutput.toJson(data.parameters) : null
        def datasetsStr = data.datasets ? JsonOutput.toJson(data.datasets) : null
        def analysis = new Analysis(alignment: alignment,
                                    tool: data.toolId,
                                    pipeline: pipeline,
                                    category: data.toolCategory,
                                    workflowId: data.workflowId,
                                    historyId: data.historyId,
                                    parameters: parameterStr,
                                    statistics: statisticsStr,
                                    datasets: datasetsStr)
        if (!analysis.save()) {
            log.error analysis.errors
            throw new AlignmentStatsException(message: "Error saving the analysis ${analysis}!")
        }

        // store named fields
        if (data.statistics) {
            def updatedInAlignment = copyProperties(data.statistics, alignment)
            if (updatedInAlignment.size() > 0) {
                if (!alignment.save()) {
                    log.error "Error saving ${updatedInAlignment} in Alignment!"
                }
            } 
            def updatedInExperiment = copyProperties(data.statistics, experiment)
            if (updatedInExperiment.size() > 0) {
                if (!experiment.save()) {
                    log.error "Error saving ${updatedInExperiment} in Experiment!"
                }
            } 
        }

        return        
    }
    
    def getPipeline(String user) {
        def pipeline = Pipeline.where {name == user}.order('pipelineVersion', 'desc').get(max: 1)
        return pipeline
    }
    
    def copyProperties(source, target) {
        def updatedProperties = []
        def readKey = source.containsKey("read") ? "read${source.read}" : "read"
        source.each { key, value ->
            if (key != "read" && target.hasProperty(key) && value != null) {
                try {
                    // Special handling of fastqFile and fastqcReport: there could be two files for each sample.
                    if ( key in ["fastqFile", "fastqcReport"] ) {
                        def dic = [:] 
                        if ( target[key] ) {
                            // If the target field has already been filled, parse the value.   
                            def jsonSlurper = new JsonSlurper()
                            try {
                                dic = jsonSlurper.parseText(target[key])
                            } catch (Exception e) {
                            }                          
                        }
                        dic[readKey] = value
                        target[key] = JsonOutput.toJson(dic)
                    } else {
                        target[key] = value
                    }
                } catch(org.codehaus.groovy.runtime.typehandling.GroovyCastException e) {
                    log.error e
                    throw new AlignmentStatsException(message: e.message)
                }                
                updatedProperties.push(key)
            }
        }
        return updatedProperties
    }

}