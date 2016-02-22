package pegr
import spock.lang.*

class DataMigrateIntegrationSpec extends Specification {
    def "data migration"() {
		given: "filename, start line and end line"
		def filename = "files/samples.csv"
		def startLine = 10289
		def endLine = 10289
		 
		when: "migration is called"
		def dataMigrate = new DataMigrate()
		//dataMigrate.migrate(filename, startLine, endLine)
		
		dataMigrate.getAllBioReplicate()
		
		then: "completes"
    }
}
