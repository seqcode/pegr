package pegr

import grails.gorm.transactions.Transactional

class StrainException extends RuntimeException {
    String message
}

class StrainService {
    @Transactional
    void save(Strain strain) {
        if (!strain.save(flush: true)) {
            throw new StrainException(message: "Invalid inputs!")
        }
    }
    
    @Transactional
    void delete(Long id) {
        try {
            Strain.executeUpdate("delete Strain t where t.id = :strainId", [strainId: id])
        }catch(Exception e) {
            log.error "Error: ${e.message}", e
            throw new StrainException(message: "Error deleting the strain!")
        }
    }
        
}
