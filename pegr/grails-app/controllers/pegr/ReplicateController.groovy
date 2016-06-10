package pegr

class ReplicateController {
    def replicateService
    def sampleService
    def springSecurity
    
    def saveAjax(String sampleIds, String type, Long currentSampleId) {
        def message = null
        try {
            def results = replicateService.save(currentSampleId, sampleIds, type)
            if (results.unknownIds.size() > 0) {
                message = "Samples ${results.unknownIds} are not found!"
            }
        } catch(ReplicateException e) {
            message = e.message
        } catch(Exception e) {
            log.error e
            message = "Error creating the replicate set!"
        }
        def sample = Sample.get(currentSampleId)
        def replicates = sampleService.getReplicates(sample)
        render template: "/sample/replicates", model: [replicates: replicates, message: message]
    }
    
    def show(Long id) {
        def set = ReplicateSet.get(id)
        if (!set) {
            render(view: "/404")
            return
        }
        def samples = ReplicateSamples.where{set.id == id}.collect{it.sample}
        if (samples.size() < 2) {
            flash.message = "There is ${samples.size()} sample in the set. Please add more samples or delete this replicate set!"
        }
        def authorized = 
        [samples: samples, set: set, authorized: authorized]
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