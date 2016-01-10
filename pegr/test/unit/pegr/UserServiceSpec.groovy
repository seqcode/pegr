package pegr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User])
class UserServiceSpec extends Specification {

    def "User basic information gets updated with valid input" () {
		given: "Existing user"
		def user = new User(username: "admin", password: "secret").save(failOnError: true)
		
		when: "Valid input is provided"
		def uic = new UserInfoCommand(fullName: "new")
		service.updateUser(uic, user.id)
		
		then: "User information is updated"
		user.fullName == "new"
    }
	
	def "Exception thrown when user basic information is updated with invalid input" () {
		given: "Existing user"
		def user = new User(username: "admin", password: "secret").save(failOnError: true)
		
		when: "Invalid input is provided"
		def uic = new UserInfoCommand(email: "new")
		service.updateUser(uic, user.id)
		
		then: "User information is updated"
		thrown(UserException)
	}

}
