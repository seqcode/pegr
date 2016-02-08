package pegr
import spock.lang.*

class DataMigrateIntegrationSpec extends Specification {
    def "data migration"() {
		given: "filename, start line and end line"
		def filename = "files/samples.csv"
		def startLine = 306
		def endLine = 306
		 
		when: "migration is called"
		def dataMigrate = new DataMigrate()
		dataMigrate.migrate(filename, startLine, endLine)
		
		then: "completes"
    }
}
