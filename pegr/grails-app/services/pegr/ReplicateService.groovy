package pegr
import grails.transaction.Transactional

class ReplicateException extends RuntimeException {
    String message
}

class ReplicateService {
    def utilityService
    
    @Transactional
    def save(String sampleIds, String type, Project project) {
        def ids
        try {
            ids = utilityService.parseSetOfNumbers(sampleIds)
        } catch (UtilityException e) {
            throw new ReplicateException(message: e.message)
        }
        def set = new ReplicateSet(type: type, project: project)
        set.save(flush: true, failOnError: true)
        def unknownIds = []
        ids.each { id ->
            def sample = Sample.get(id)
            if (sample && ProjectSamples.findBySampleAndProject(sample, project)) {                
                new ReplicateSamples(set: set, sample: sample).save()
            } else {
                unknownIds << id
            }
        }
        return [setId: set.id, unknownIds: unknownIds]
    }
    
    @Transactional
    def addSamples(Long setId, String sampleIds) {
        def set = ReplicateSet.get(setId)
        def unknownIds = []
        if (!set) {
            throw new ReplicateException(message: "Replicate set not found!")
        }
        if (sampleIds == null || sampleIds == "") {
            throw new ReplicateException(message: "Empty input!")
        }
        def ids
        try {
            ids = utilityService.parseSetOfNumbers(sampleIds)
        } catch (UtilityException e) {
            throw new ReplicateException(message: e.message)
        }
        ids.each { id ->
            def sample = Sample.get(id)
            if (sample && ProjectSamples.findBySampleAndProject(sample, set.project)) {
                new ReplicateSamples(set: set, sample: sample).save()
            } else {
                unknownIds << id
            }
        }
        return unknownIds
    }
    
    @Transactional
    def removeSample(Long setId, Long sampleId) {
        def repSample = ReplicateSamples.where {set.id == setId && sample.id == sampleId}.find()
        if (repSample) {
            repSample.delete()
            return true
        } else {
            return false
        }
    }
    
    @Transactional
    def delete(Long id) {
        def set = ReplicateSet.get(id)
        if (!set) {
            throw new ReplicateException(message: "Replicate not found!")
        } else {
            def projectId = set.project?.id
            def repSamples = ReplicateSamples.findAllBySet(set)
            repSamples.each {
                it.delete()
            }
            set.delete()
            return projectId
        }        
    }
    
    def getReplicates(Sample sample) {
        def replicateSets = ReplicateSamples.findAllBySample(sample).collect{it.set}
        def bioReps = replicateSets.findAll {it.type == ReplicateType.BIOLOGICAL}
        def techReps = replicateSets.findAll {it.type == ReplicateType.TECHNICAL}
        [Biological: bioReps, Technical: techReps]
    }
    
    def getReplicates(Project project) {
        def replicateSets = ReplicateSet.findAllByProject(project)
        def bioReps = replicateSets.findAll {it.type == ReplicateType.BIOLOGICAL}
        def techReps = replicateSets.findAll {it.type == ReplicateType.TECHNICAL}
        [Biological: bioReps, Technical: techReps]
    }
}