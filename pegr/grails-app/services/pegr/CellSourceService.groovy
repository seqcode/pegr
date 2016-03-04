package pegr

import grails.transaction.Transactional

class CellSourceException extends RuntimeException {
    String message
}

class CellSourceService {
    
    @Transactional
    def save(CellSource cellSource) {
        try {
            cellSource.save(flush: true)
        } catch (Exception E) {
            throw new CellSourceException(message: "Invalid inputs!")
        }
        
    }
}
