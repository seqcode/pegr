package pegr

class WalleJob {
    static triggers = {
      simple name: 'sendSequenceRunToWalle', startDelay: 1000 * 60 * 1, repeatInterval: 1000 * 60 * 60, repeatCount: -1 // execute job once every hour
    }

    def concurrent = false

    def execute() {
        // execute job
        def walleService = new WalleService()
        try {
            log.info "WallE service starts to create job."
            walleService.createJob()
            log.info "WallE service has finished creating job."
        } catch (Exception e) {
            log.error "Error: ${e.message}", e
        }
    }
}
