package pegr

class NgsRepoJob {
    static triggers = {
      simple name: 'sendSequenceRunToNgsRepo', startDelay: 1000 * 60 * 5, repeatInterval: 1000 * 60 * 15, repeatCount: -1 // execute job once every 15 min
    }

    static concurrent = false

    def ngsRepoService

    void execute() {
        // execute job
        try {
            log.warn "NGS repository service starts to create job."
            ngsRepoService.createJob()
        } catch (Exception e) {
            log.error "Error: ${e.message}", e
        }
    }
}
