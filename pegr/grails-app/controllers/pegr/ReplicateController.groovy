package pegr

class ReplicateController {
    replicateService
    
    def create(ReplicateType type) {        
    }
    
    def save(String sampleIds, ReplicateType type) {
        try {
            def results = replicateService.save(sampleIds, type)
            if (results.unknownIds.size() > 0) {
                flash.message = "Samples ${results.unknownIds} are not found!"
            } else {
                flash.message = "Success! Replicate set has been created."
            }
            redirect(action: "show", id: results.setId)
        } catch(ReplicateException e) {
            flash.message = e.message
            redirect(action: "create")
        } catch(Exception e) {
            log.error e
            flash.message = "Error creating the replicate set!"
            redirect(action: "create")
        }
    }
    
    def show(Long id) {
        def samples = ReplicateSamples.where{set.id == id}
        if (samples.size() < 2) {
            flash.message = "There is ${samples.size()} sample in the set. Please add more samples or delete this replicate set!"
        }
        [samples: samples]
    }
    
    def addSamples(Long setId, Long sampleIds) {
        try {
            def unknownIds = replicateService.addSamples(setId, sampleIds)
            if (unknownIds.size() > 0) {
                flash.message = "Samples ${unknownIds} are not found!"
            } else {
                flash.message = "Success! Samples have been added."
            }
        } catch (ReplicateException e) {
            flash.message = e.message
        } catch (Exception e) {
            log.error e
            flash.message = "Error adding the samples!"
        }
        redirect(action: "show", id: setId)
    }
    
    def removeSample(Long setId, Long sampleId) {
        try {
            if (replicateService.removeSample(setId, sampleId)) {
                flash.message = "Success! Sample has been removed."
            } else {
                flash.message = "Sample or set not found!"
            }                        
        } catch (Exception e) {
            log.error e
            flash.message = "Error Removing the sample!"
        }
        redirect(action: "show", id: setId)
    }
    
    def delete(Long setId) {
        try {
            replicateService.delete(setId)
        } catch (ReplicateException e) {
            flash.message = e.message
        } catch (Exception e) {
            log.error e
            flash.message = "Error deleting the set!"
        }
        redirect(controller: "project", action: "index")
    }
}