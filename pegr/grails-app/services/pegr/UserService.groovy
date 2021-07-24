package pegr
import grails.transaction.Transactional
import groovy.sql.Sql

class UserException extends RuntimeException {
    String message
    User user
}

class UserService {
    def springSecurityService
    def utilityService
    def mailService
    def grailsApplication
    def grailsLinkGenerator
    def dataSource
    
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
    def create(String username, String email, List groupIds, Boolean sendEmail, String fullName=null){
        if(User.findByEmail(email)) {
            throw new UserException(message: "Email has already been used!")
        }

        if (User.findByUsername(username)) {
            throw new UserException(message: "Username ${username} has already been registered with PEGR!")
        }   
        
        // assign a random password
        def password = springSecurityService.encodePassword(utilityService.getRandomString(15))
        
        def user = new User(username: username, 
                            email: email, 
                            password: password,
                            accountLocked: false,
                            enabled: true,
                            fullName: fullName
                           )        
        user.save(flush: true, failOnError: true)
        
        // add user to role groups
        groupIds.each { groupId->
            def group = RoleGroup.get(groupId)
            if (group) {
                new UserRoleGroup(user: user, roleGroup: group).save()
            }
        }

        // send email
        if (sendEmail) {
            def length = 32
            def token = utilityService.getRandomString(length)
            new Token(token: token, user: user, date: new Date()).save()
            
            def url = grailsLinkGenerator.link(controller: 'user', action: 'register', params: [token:token], absolute: true)
            def text = "Your account has been created in PEGR. Please follow the link ${url} to set up your username and password."
            
            try {
                mailService.sendMail {
                   to email
                   subject "[PEGR] Account Information"
                   body text
                }
            } catch (Exception e) {
                throw new UserException(message: "Error sending the email!")
            }
        }        
        return user
    }
    
    @Transactional
    def unlockAccount(UserRegistrationCommand urc) {
        def userToken = Token.findByToken(urc.token)
        if (!userToken) {
            throw new UserException(message: "Invalid link!")
        }
        def user = userToken.user
        userToken.delete()
        if (urc.email != user.email) {
            throw new UserException(message: "Email does not match the one registered with the system!")
        } else if (urc.hasErrors()) {
            throw new UserException(message: "Input error!")
        } else if(user.username != urc.username && User.findByUsername(urc.username)) {
            throw new UserException(message: "Username already exists!")
        } else {
            user.username = urc.username
            user.password = springSecurityService.encodePassword(urc.password)
            user.accountLocked = false
            if (!user.save(flush:true)) {
                throw new UserException(message: "Error activating the account!")
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
    def getToken(String email) {
        def user = User.findByEmail(email)
        if (!user) {
            throw new UserException(message: "User not found!")
        }
        def length = 32
        def token = utilityService.getRandomString(length)
        new Token(token: token, user: user, date: new Date()).save()
        return token
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
    
    @Transactional
    def mergeUsers(List fromUsernames, String toUsername) {
        fromUsernames.each { fromUsername ->
            mergeUser(fromUsername, toUsername)
        }
    }
    
    @Transactional
    def mergeUser(String fromUsername, String toUsername) {
        def fromUser = User.findByUsername(fromUsername)        
        if (!fromUser) {
            throw new UserException(message: "User ${fromUsername} does not exist!")
        }
        
        def toUser = User.findByUsername(toUsername)
        if (!toUser) {
            throw new UserException(message: "User ${toUsername} does not exist!")
        }
        
        def sql = new Sql(dataSource)
        try {
            // simple many-to-one relation
            utilityService.updateForeignKeyInDb("cell_source", "provider_user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("cell_source", "prep_user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("organization", "billing_contact", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("organization", "pi", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("protocol_group", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("sequence_run", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("analysis", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("item", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("sample", "send_data_to", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("protocol_instance_summary", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("history", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("run_stats", "technician", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("inventory", "receiving_user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("protocol_instance", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("token", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("cell_source_batch", "user", fromUser.id, toUser.id, sql)
            utilityService.updateForeignKeyInDb("protocol", "user", fromUser.id, toUser.id, sql)
            
            // many-to-many tables
            utilityService.updateLinksInDb("project_user", "project", 'user', fromUser.id, toUser.id, sql)            
            sql.execute("delete from user_role where user_id = ?", [fromUser.id])
            sql.execute("delete from user_role_group where user_id = ?", [fromUser.id])
            fromUser.delete()
            sql.close()
        } catch(Exception e) {
            sql.close()
            log.error e.toString()
            throw new UserException(message: "Error merging user ${fromUsername} to user ${toUsername}!")
        }
    }
}
