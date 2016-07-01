package pegr.admin
import pegr.AdminCategory;
import pegr.User

class UserAdminController {
	public static AdminCategory category = AdminCategory.OTHER
	
    def userService
    
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[users: User.list(params), count: User.count()]
	}
	
	def inactiveUsers() {
		params.max = Math.min(max ?: 10, 100)
		[users: User.list(params), count: User.count()]
	}
	
    def activateUserAjax(Long userId) {
        
    }
    
    def deactivateUserAjax(Long userId) {
        
    }
    
    def changeUserRoleAjax(Long userId, Long roleId) {
        
    }
	
}
