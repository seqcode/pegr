package pegr
import grails.transaction.Transactional

class SequenceRunException extends RuntimeException {
    String message
}

class SequenceRunService {
    @Transactional
    void save(SequenceRun run) {
        if(!run.save(flush: true)) {
            throw new SequenceRunException(message: "Invalid input!")
        }
    }
}