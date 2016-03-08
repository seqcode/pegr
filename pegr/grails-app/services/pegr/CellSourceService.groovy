package pegr

import grails.transaction.Transactional

class CellSourceException extends RuntimeException {
    String message
}

class CellSourceService {
    def itemService
    
    @Transactional
    def save(CellSource cellSource, List treatments) {
        if (cellSource.strain && !cellSource.strain.id) {
            // save species if it's new
            def species = cellSource.strain.species
            if(species && !species.id) {
                if(!species.save(flush:true)) {
                    throw new ItemException(message: "Invalid inputs for species!")
                }
            }
            // save strain if it's new
            if (!cellSource.strain.save(flush:true)) {
                throw new ItemException(message: "Invalid inputs for strain!")
            }
        }
        // save cell source
        if (!cellSource.save(flush: true)) {
            throw new ItemException(message: "Invalid inputs for cell source!")
        }
        // save cell source's treatments
        def toDelete = CellSourceTreatments.where{cellSource == cellSource}.list()
        treatments.each {
            def treatmentId = Long.parseLong(it)
            def oldTreatment = toDelete.find{it.treatment.id == treatmentId}
            if (oldTreatment) {
                toDelete.remove(oldTreatment)
            } else {
                def treatment = CellSourceTreatment.get(treatmentId)
                if (treatment) {
                    new CellSourceTreatments(cellSource: cellSource, treatment: treatment).save()
                } else {
                    throw new ItemException(message: "Treatment not found!")
                }
            }
        }
        toDelete.each {
            it.delete()
        }
    }       
    
    @Transactional
    def save(Item item, CellSource cellSource, List treatments){
        itemService.save(item) 
        cellSource.item = item 
        save(cellSource, treatments)
    }
    
    @Transactional
    def addTreatment(CellSourceTreatment treatment) {
        if(!treatment.save(flush: true)) {
            throw new CellSourceException(message: "Invalid inputs for treatment!")
        }
    }

}
