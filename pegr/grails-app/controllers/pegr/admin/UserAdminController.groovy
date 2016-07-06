package pegr.admin
import pegr.AdminCategory
import pegr.UserRole
import pegr.User
import pegr.UserService
import pegr.UserException

class UserAdminController {
	public static AdminCategory category = AdminCategory.OTHER
	
    def userService
    
	def index(Integer max, Long affiliationId, Long roleId, String isEnabled) {
		params.max = Math.min(max ?: 25, 100)
        
        def affiliations = User.where{}.collect { it.affiliation }.unique()

        def users 
        def totalCount
        
        if (roleId) {
            def sort = params.sort ? ("user."+params.sort) : "user.fullName"
            users = UserRole.createCriteria().list(max: params.max, offset: params.offset, sort: sort, order: params.order) {
                eq("role.id", roleId) 
            }.collect { it.user }
            
            totalCount = UserRole.where { role.id == roleId }.count()
        } else {
            def query
            if (affiliationId) {
                query = User.where { affiliation.id == affiliationId }         
            } else if (isEnabled != null && isEnabled != "") {
                query = User.where { enabled == isEnabled }
            } else {
                query = User.where{}
            }
            users = query.list(params)
            totalCount = query.count()
        }

		[users: users, 
         totalCount: totalCount, 
         affiliations: affiliations,
         affiliationId: affiliationId,
         roleId: roleId,
         isEnabled: isEnabled]
	}
	
    
    def edit(Long userId) {
        def user  = User.get(userId)
        if (!user) {
            render(view: "/404")
            return
        } 
        [user: user]
    }
    
    def update(Long userId) {
        log.error userId
        def user = User.get(userId)
        if (!user) {
            render(view: "/404")
            return
        } 
        user.properties = params
        def roles = params.list("roles")
        try {
            userService.updateByAdmin(user, roles)
            flash.message = "Success updating the user!"
        } catch(UserException e) {
            flash.message = e.message
        }
        redirect(action:"index")
        
    }
	
}
