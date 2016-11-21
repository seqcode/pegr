package pegr

import grails.transaction.Transactional

class UserException extends RuntimeException {
    String message
    User user
}

class UserService {
    def springSecurityService
    def utilityService
    
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
    def updateByAdmin(User user, List groups) {
        user.save()
        def toDelete = UserRoleGroup.findAllByUser(user)
        groups.each { groupId ->
            def group = RoleGroup.get(groupId)
            if (group) {
                def old = toDelete.find { it.roleGroup == group }
                if (old) {
                    toDelete.remove(old)
                } else {
                    new UserRoleGroup(user: user, roleGroup: group).save()
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
        } else if(User.findByEmail(urc.email)) {
            throw new UserException(message: "Email has already been used!")
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
        final int length = 32
        final int maxAttempt = 10
        
        // generate a random string as the API key
        String randomString
        for (int i=0; i< maxAttempt; ++i) {
            randomString = utilityService.getRandomString(length)
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
    
    @Transactional
    def save(User user) {
        if (!user.save()) {
            throw new UserException(message: "Error saving the user!")
        }
    }
    
    @Transactional
    def updateAddress(User user, Address address) {
        if (user.address) {
            user.address.properties = address
        } else {
            user.address = address
        }
        if (!user.address.save()) {
            throw new UserException(message: "Error saving the address!")
        }
    }
    
    @Transactional
    def deleteAddress(User user) {
        user.address.delete()
        user.address = null
        user.save()
    }
    
    @Transactional
    def updatePassword(User user, PasswordRegistrationCommand urc) {
        urc.validate()
        if (urc.hasErrors()) {
            throw new UserException(message: "Invalid input!")
        } else {                                   
            user.password = springSecurityService.encodePassword(urc.password)
            user.save()
        }
    }
    
    @Transactional
    def sendResetPasswordEmail(String email, String baseUrl) {
        def user = User.findByEmail(email)
        if (!user) {
            throw new UserException(message: "User not found!")
        }
        def length = 32
        def token = utilityService.getRandomString(length)
        new Token(token: token, user: user, date: new Date()).save()
        def url = baseUrl + "?token=" + token
        EmailResetPasswordJob.triggerNow([email: email, url: url])
    }
    
    @Transactional
    def resetPasswordByToken(String token, PasswordRegistrationCommand urc) {
        def userToken = Token.findByToken(token)
        if (!userToken) {
            throw new UserException(message: "Invalid link!")
        }
        updatePassword(userToken.user, urc)
        userToken.delete()
    }
}
