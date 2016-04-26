package pegr
import grails.transaction.Transactional

class AlignmentStatsException extends RuntimeException {
    String message
}

class AlignmentStatsService {
    @Transactional
    void save(StatsRegistrationCommand newStats) {
        def alignment = SequenceAlignment.where { sequencingExperiment.sequenceRun.id == newStats.run && sequenceingExperiment.sample.id == newStats.sample && genome.name == newStats.genome}.find()
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
        } else {
            throw new AlignmentStatsException(message: "Sequence Alignment not found!")
        }
        
    }
    
    def copyProperties(source, target) {
        source.properties.each { key, value ->
            if (target.hasProperty(key) && !(key in ['class', 'metaClass'])) 
                target[key] = value
        }
    }
}