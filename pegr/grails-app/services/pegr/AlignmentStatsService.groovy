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
                if (alignmentStats) {
                    copyProperties(newStats, alignmentStats)
                    alignmentStats.save()
                } else {
                    alignmentStats = new AlignmentStats()
                    copyProperties(newStats, alignmentStats)
                    alignmentStats.save(flush:true)
                    alignment.alignmentStats = alignmentStats
                    alignment.save()
                }
                return alignmentStats
            }
        } 
        throw new AlignmentStatsException(message: "Sequence Alignment not found!")
    }
    
    def copyProperties(source, target) {
        source.properties.each { key, value ->
            if (target.hasProperty(key) && !(key in ['class', 'metaClass'])) 
                target[key] = value
        }
    }
}