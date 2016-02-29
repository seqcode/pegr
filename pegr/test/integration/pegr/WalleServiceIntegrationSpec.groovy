package pegr
import spock.lang.*

class WalleServiceIntegrationSpec extends Specification {
    def walleService 
    
    def "create walle job"() {
		given: "walle config"
        walleService.addToQueue(194)
            
		when: "create job"
        walleService.createJob()
		
		then: "completes"
    }
}

