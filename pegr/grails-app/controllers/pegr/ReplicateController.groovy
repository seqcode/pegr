package pegr

class ReplicateController {
    def replicateService
    def projectService
    def sampleService
    def springSecurity
    
    def save(String sampleIds, String type, Long sampleId) {
        try {
            sampleIds = "${sampleId},${sampleIds}"
            replicateService.save(sampleIds, type)
        } catch(ReplicateException e) {
            flash.message = e.message
        } catch(Exception e) {
            log.error e.toString()
            flash.message = "Error saving the replicate samples!"
        }        
        redirect(action: "edit", params: [sampleId: sampleId, type: type])
    }
    
    def edit(String type, Long sampleId) {
        def sample = Sample.get(sampleId)
        def replicates = replicateService.getReplicates(sample, type)
        [replicates: replicates.join(","), sampleId: sampleId, type: type]
    }
  
}