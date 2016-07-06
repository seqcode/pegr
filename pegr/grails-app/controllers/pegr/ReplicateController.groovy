package pegr

class ReplicateController {
    def replicateService
    def projectService
    def sampleService
    def springSecurity
    
    def saveAjax(String sampleIds, String type, Long projectId) {
        def message = null
        def project = Project.get(projectId)
        if (!project) {
            render status: 404
            return false
        }
        try {
            def results = replicateService.save(sampleIds, type, project)
            if (results.unknownIds.size() > 0) {
                message = "Samples ${results.unknownIds} are not found or do not belong to the project!"
            }
        } catch(ReplicateException e) {
            message = e.message
        } catch(Exception e) {
            log.error e
            message = "Error creating the replicate set!"
        }        
        def replicates = replicateService.getReplicates(project)
        render template: "/replicate/list", model: [replicates: replicates, message: message]
    }
    
    def show(Long id) {
        def set = ReplicateSet.get(id)
        if (!set) {
            render(view: "/404")
            return
        }
        def samples = ReplicateSamples.where{set.id == id}.collect{it.sample}
        def authorized = false
        if (set.project) {
            authorized = projectService.authToEdit(set.project)
        }
        [samples: samples, set: set, authorized: authorized]
    }
    
    def addSamples(Long setId, String sampleIds) {
        try {
            def unknownIds = replicateService.addSamples(setId, sampleIds)
            if (unknownIds.size() > 0) {
                flash.message = "Samples ${unknownIds} are not found or do not belong to the same project!"
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
    
    def delete(Long id) {
        try {
            def projectId = replicateService.delete(id)
            flash.message = "Success! Replicate set ${id} has been delete."
            redirect(controller: "project", action: "show", id: projectId)
        } catch (ReplicateException e) {
            flash.message = e.message
            redirect(action: "show", id: id)
        } catch (Exception e) {
            log.error e
            flash.message = "Error deleting the set!"
            redirect(action: "show", id: id)
        }
        
    }
}