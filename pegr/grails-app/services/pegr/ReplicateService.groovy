package pegr
import grails.gorm.transactions.Transactional

class ReplicateException extends RuntimeException {
    String message
}

class ReplicateService {
    def utilityService
    
    @Transactional
    def save(String sampleIds, String type) {
        def ids
        try {
            ids = utilityService.parseSetOfNumbers(sampleIds)
        } catch (UtilityException e) {
            throw new ReplicateException(message: e.message)
        }
        
        def c = ReplicateSamples.createCriteria()
        def rs = c.list {
            and {
                sample {
                    'in' "id", ids
                }
                set {
                    eq "type", ReplicateType.valueOf(type.toUpperCase())
                }
            }                      
            maxResults(1)
        }
        
        def set
        if (rs.size() > 0) {
            set = rs.first().set
        } else {
            set = new ReplicateSet(type: ReplicateType.valueOf(type.toUpperCase()))
            set.save(flush: true, failOnError: true)
        }
        
        def old_rs = ReplicateSamples.findAllBySet(set)
        old_rs.each { 
            if (!ids.contains(it.sample.id)) {
                it.delete()
            }
        }
        
        ids.each { id ->
            def sample = Sample.get(id)
            if (sample) {      
                if (!ReplicateSamples.findBySetAndSample(set, sample)) {
                    new ReplicateSamples(set: set, sample: sample).save()
                }                
            } else {
                throw new ReplicateException(message: "Sample ${id} not found!")
            }
        }
    }
    
    def getReplicates(Sample sample) {
        def replicateSets = ReplicateSamples.findAllBySample(sample).collect{it.set}
        def result = [:]
        [ReplicateType.BIOLOGICAL, ReplicateType.TECHNICAL, ReplicateType.SEQUENCING].each { type ->
            def set = replicateSets.find {it.type == type}
            result[type] = ReplicateSamples.findAllBySetAndSampleNotEqual(set, sample).collect{it.sample.id}
        }        
        return result
    }
    
    def getReplicates(Sample sample, String type) {
        def replicateSets = ReplicateSamples.findAllBySample(sample).collect{it.set}
        def set = replicateSets.find {it.type == ReplicateType.valueOf(type.toUpperCase())}
        
        def result = []
        if (set) {
            result = ReplicateSamples.findAllBySetAndSampleNotEqual(set, sample).collect{it.sample.id}
        }
        
        return result
    }
    
}