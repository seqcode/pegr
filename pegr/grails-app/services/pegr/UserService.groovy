package pegr

import grails.transaction.Transactional

class UserException extends RuntimeException {
    String message
    User user
}

class UserService {
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
}
