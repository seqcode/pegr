package pegr

class EmailResetPasswordJob {
    static triggers = {}

    def concurrent = false
    
    def emailService
    
    def execute(context) {        
        def to = context.mergedJobDataMap.get('email')
        def url = context.mergedJobDataMap.get('url')
        def subject = "[PEGR]Reset password"
        def body = "Please follow the link " + url + " to reset your password"
        try {
            emailService.send(to, subject, body)
        } catch(Exception e) {
            log.error e
        }
    }
}
