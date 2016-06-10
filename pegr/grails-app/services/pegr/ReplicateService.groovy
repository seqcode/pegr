package pegr
import grails.transaction.Transactional

class ReplicateException extends RuntimeException {
    String message
}

class ReplicateService {
    def utilityService
    
    def save(Long currentSampleId, String sampleIds, String type) {
        def ids
        try {
            ids = utilityService.parseSetOfNumbers(sampleIds)
        } catch (UtilityException e) {
            throw new ReplicateException(message: e.message)
        }
        def set = new ReplicateSet(type: type)
        set.save(flush: true, failOnError: true)
        def unknownIds = []
        ids << currentSampleId
        ids.each { id ->
            def sample = Sample.get(id)
            if (sample) {
                new ReplicateSamples(set: set, sample: sample).save()
            } else {
                unknownIds << id
            }
        }
        return [setId: set.id, unknownIds: unknownIds]
    }
    
    def addSamples(Long setId, Long sampleIds) {
        def set = ReplicateSet.get(setId)
        if (!set) {
            throw new ReplicateException(message: "Replicate set not found!")
        }
        def ids
        try {
            ids = utilityService.parseSetOfNumbers(sampleIds)
        } catch (UtilityException e) {
            throw new ReplicateException(message: e.message)
        }
        ids.each { id ->
            def sample = Sample.get(id)
            if (sample) {
                new ReplicateSamples(set: set, sample: sample).save()
            } else {
                unknownIds << id
            }
        }
        return unknownIds
    }
    
    def removeSample(Long setId, Long sampleId) {
        def repSample = ReplicateSamples.where {set.id == setId && sample.id == sampleId}.find()
        if (repSample) {
            repSample.delete()
            return true
        } else {
            return false
        }
    }
    
    def delete(Long setId) {
        def set = ReplicateSet.get(setId)
        if (!set) {
            throw new ReplicateException(message: "Replicate not found!")
        } else {
            set.delete()
        }
    }
}