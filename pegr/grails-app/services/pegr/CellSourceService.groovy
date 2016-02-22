package pegr

import grails.transaction.Transactional

class CellSourceException extends RuntimeException {
    String message
}

class CellSourceService {

    @Transactional
    def updateTreatments(CellSource cellSource, List treatments) {
        def changes = [:]
        def oldTreatments = cellSource.treatments
        oldTreatments.each {
            changes[it.id] = -1 
        }
        treatments.each{
            if(changes.containsKey(it)) {
                changes[it] = 0
            } else {
                changes[it] = 1
            }
        }
        changes.each{ key, value ->
            if(value == -1) {
                CellSourceTreatments.where{cellSource == cellSource && treatment.id == key}.get(max: 1).delete()
            }else if(value == 1) {
                def newTreatment = CellSourceTreatment.get(key)
                new CellSourceTreatments(cellSource: cellSource, treatment: newTreatment).save()
            }
        }
    }
    
    @Transactional
    def save(CellSource cellSource) {
        try {
            cellSource.save(flush: true)
        } catch (Exception E) {
            throw new CellSourceException(message: "Invalid inputs!")
        }
        
    }
}
