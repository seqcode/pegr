package pegr
import grails.transaction.Transactional

class ProjectException extends RuntimeException {
    String message
}

class ProjectService {
    
    @Transactional
    void addSamples() {
        
    }
}