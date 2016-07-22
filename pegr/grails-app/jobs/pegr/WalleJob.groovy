package pegr

class WalleJob {
    static triggers = {
      simple name: 'sendSequenceRunToWalle', startDelay: 1000 * 60 * 5, repeatInterval: 1000 * 60 * 30, repeatCount: -1 // execute job once every 30 min
    }

    def concurrent = false
    
    def walleService

    def execute() {
        // execute job
        try {
            log.warn "WallE service starts to create job."
            walleService.createJob()
        } catch (Exception e) {
            log.error "Error: ${e.message}", e
        }
    }
}
