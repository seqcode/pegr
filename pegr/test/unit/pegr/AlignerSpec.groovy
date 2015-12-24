package pegr

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Aligner)
class AlignerSpec extends Specification {
	def "Get the string given an aligner instance" () {
		given: "an alinger instance with software and alignerVersion"
		Aligner aligner = new Aligner(
			software: "AlignerSoftware",
			alignerVersion: "1.0.0")
		
		when: "toString is invoked"
		def s = aligner.toString()
		
		then: "the returned string is correct"
		s == "AlignerSoftware1.0.0"
	}

}
