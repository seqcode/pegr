package pegr

class WalleJob {
    static triggers = {
      simple name: 'sendSequenceRunToWalle', startDelay: 1000 * 60 * 10, repeatInterval: 1000 * 60 * 60, repeatCount: -1 // execute job once every hour
    }

    def concurrent = false
    
    def execute() {
        // execute job
        def walleService = new WalleService()
        walleService.createJob()
    }
}
