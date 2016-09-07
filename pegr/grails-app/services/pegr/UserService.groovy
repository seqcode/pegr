package pegr

import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

class UserException extends RuntimeException {
    String message
    User user
}

class UserService {
    def springSecurityService
    
    @Transactional
    User updateUser(UserInfoCommand uic, Long userId){

        def user = User.get(userId)
        if (!user) {
            throw UserException(message: "User not found!")
        }
        user.properties = uic.properties
        try {
            if (user.save(flush: true)) {
                return user
            } else {
                throw new UserException(message: "Invalid inputs!")
            }
        } catch(org.springframework.dao.OptimisticLockingFailureException e) {
            throw new UserException(message: "Oops! Please try again!")
        }
        
    }
    
    @Transactional
    def updateByAdmin(User user, List roles) {
        user.save()
        def toDelete = UserRole.findAllByUser(user)
        roles.each { roleId ->
            def role = Role.get(roleId)
            if (role) {
                def old = toDelete.find { it.role == role }
                if (old) {
                    toDelete.remove(old)
                } else {
                    new UserRole(user: user, role: role).save()
                }   
            }                     
        }
        toDelete.each {
            it.delete()
        }
    }
    
    @Transactional
    def create(UserRegistrationCommand urc) {
        if (urc.hasErrors()) {
            throw new UserException(message: "Input error!")
        } else if(User.findByUsername(urc.username)) {
            throw new UserException(message: "Username already exists!")
        } else {
            def user = new User(urc.properties)
            user.password = springSecurityService.encodePassword(urc.password)
            if (!user.save(flush:true)) {
                throw new UserException(message: "Error creating the user!")              
            }
        }
    }
    
    /**
     * Generate API key for the current user
     */
    @Transactional
    def generateApiKey() {
        // get the current user
        def user = springSecurityService.currentUser
        if (!user) {
            throw new UserException("Not logged in!")
        }
        
        // API key configs
        String charset = (('A'..'Z') + ('0'..'9') ).join()
        final int length = 32
        final int maxAttempt = 10
        
        // generate a random string as the API key
        String randomString
        for (int i=0; i< maxAttempt; ++i) {
            randomString = RandomStringUtils.random(length, charset.toCharArray())
            // avoid duplicate API keys
            if (!User.findByApiKey(randomString)) {
                user.apiKey = randomString
                user.save()
                break
            }
        }
        if (!user.apiKey) {
            throw new UserException(message: "Error generating the API key!")
        }
        
    }
}
