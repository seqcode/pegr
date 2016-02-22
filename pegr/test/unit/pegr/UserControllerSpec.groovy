package pegr

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserController)
@Mock([User, Address])
class UserControllerSpec extends Specification {
    def "Get the profile of the current user" () {
        given: "a logged in user"
        def user = new User(id: 1, username: 'testUser', password:'secretpwd').save(failOnError: true)
        controller.springSecurityService = [currentUser: user]
        
        when: "profile is invoked"
        def model = controller.profile()
        
        then: "the user is returned in model"
        model.user.id == 1
    }
    
    def "Redirect to login if the user is not logged in" () {
        given: "no logged in user"
        controller.springSecurityService = [:]
        
        when: "profile is invoked"
        def model = controller.profile()
        
        then: "redirected to login page"
        response.redirectedUrl == '/login/form'
    }
}