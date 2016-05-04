package pegr
import grails.transaction.Transactional

class AlignmentStatsException extends RuntimeException {
    String message
}

class AlignmentStatsService {
    @Transactional
    def save(StatsRegistrationCommand newStats) {
        def experiment = SequencingExperiment.where {sequenceRun.id == newStats.run && sample.id == newStats.sample}.find()
        def genome = Genome.findByName(newStats.genome)
        if (experiment && genome) {
            def alignment = SequenceAlignment.findBySequencingExperimentAndGenome(experiment, genome)
            if (alignment) {
                def alignmentStats = alignment.alignmentStats
                def results
                if (alignmentStats) {
                    results = copyProperties(newStats, alignmentStats)
                    alignmentStats.save()
                } else {
                    alignmentStats = new AlignmentStats()
                    results = copyProperties(newStats, alignmentStats)
                    alignmentStats.save(flush:true)
                    alignment.alignmentStats = alignmentStats
                    alignment.save()
                }
                return results
            }
        } 
        throw new AlignmentStatsException(message: "Sequence Alignment for Run ${newStats.run}, Sample ${newStats.sample} and Genome ${newStats.genome} not found!")
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