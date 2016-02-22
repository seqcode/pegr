package pegr

import grails.transaction.Transactional

class UserException extends RuntimeException {
    String message
    User user
}

@Transactional
class UserService {
    
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
}
