package pegr

import grails.transaction.Transactional

class StrainException extends RuntimeException {
    String message
}

class StrainService {
    @Transactional
    void save(Strain strain, List geneticModificationIds) {
        if (strain.save(flush: true)) {
            def changes = [:]
            def oldModificationIds = StrainGeneticModifications.where{strain == strain}.collect{it.geneticModification.id}
            oldModificationIds.each {
                changes[it] = -1                
            }
            geneticModificationIds.each {
                if (changes.containsKey(it)) {
                    changes[it] = 0
                } else {
                    changes[it] = 1
                }
            }
            changes.each{ key, value ->
                if (value == -1) {
                    StrainGeneticModifications.where{strain == strain && geneticModification.id == key}.get(max: 1).delete()
                }else if (value == 1){
                    def newModification = GeneticModification.get(key)
                    new StrainGeneticModifications(strain:strain, geneticModification: newModification).save()
                }
            }
        }else {
            throw new StrainException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void delete(Long id) {
        try {
            StrainGeneticModifications.executeUpdate("delete StrainGeneticModifications t where t.strain.id = :strainId", [strainId: id])
            Strain.executeUpdate("delete Strain t where t.id = :strainId", [strainId: id])
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new StrainException(message: "Error deleting the strain!")
        }
    }
        
}
