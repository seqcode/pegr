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
        log.error data.run
        def pipeline = getPipeline(apiUser)
        // check required fields
        def requiredFields = ["run", "sample", "genome", "toolId", "workflowId", "toolCategory"]
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
        def analysis = new Analysis(alignment: alignment,
                                    tool: data.toolId,
                                    pipeline: pipeline,
                                    category: data.toolCategrory,
                                    workflowId: data.workflowId,
                                    parameters: data.parameters,
                                    statistics: data.statistics,
                                    datasets: data.datasets)
        analysis.save()

        // store named fields
        if (data.statistics) {
            copyProperties(data.statistics, alignment)
            alignment.save()

            copyProperties(data.statistics, experiment)
            experiment.save()   
        }

        return        
    }
    
    def getPipeline(String user) {
        def pipeline = Pipeline.where {name == user}.order('pipelineVersion', 'desc').get(max: 1)
        return pipeline
    }
    
    def copyProperties(source, target) {
        def updatedProperties = []
        source.properties.each { key, value ->
            if ( ! (key in ['run', 'sample', 'genome', 'class', 'metaClass']) && target.hasProperty(key) && value) {
                target[key] = value
                updatedProperties.push(key)
            }
        }
        return updatedProperties
    }
}