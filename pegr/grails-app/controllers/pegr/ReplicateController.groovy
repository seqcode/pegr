package pegr

class ReplicateController {
    def showReplicates(Long id) {
        def samples = ReplicateSamples.where{set.id == id}
        [samples: samples]
    }
    
    def addSample(Long setId, Long sampleId) {
        
    }
    
    def removeSample(Long setId, Long sampleId) {
        
    }
}