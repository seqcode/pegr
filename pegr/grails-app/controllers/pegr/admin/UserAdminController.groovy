package pegr.admin
import pegr.AdminCategory
import pegr.Organization
import pegr.RoleGroup
import pegr.UserRoleGroup
import pegr.User
import pegr.UserService
import pegr.UserException
import grails.converters.*
import grails.util.Holders
import groovy.json.*

class UserAdminController {
	public static AdminCategory category = AdminCategory.OTHER

    def userService

	def index(Long affiliationId, Long groupId, String isEnabled) {

	  			def query
					def users
	        def affiliations = User.where{}.collect { it.affiliation }.unique()

				if (params.groupLoad != null && params.affiliateLoad != null && params.activityLoad != null){
					def groupList = JSON.parse(params.groupLoad)
					def affiliateList = JSON.parse(params.affiliateLoad)
					def activityList = JSON.parse(params.activityLoad)
					def c
					if (groupList.size() != 0 || affiliateList.size() != 0 || activityList.size() != 0){
					// def a = RoleGroup.where{name == "Admin"}.collect { it.id }.unique()

					// groupList start
					def i = 0;
					def a = RoleGroup.createCriteria().list{
						if (groupList.size() != 0){
							or{
								for (i=0; i<groupList.size(); i++){
										eq("name", groupList[i])
								}
							}
						}
					}.collect { it.id }
					// get username of users who meet group criteria
					c = UserRoleGroup.createCriteria().list{
						if (groupList.size() != 0){
							or{
								for (i=0; i<a.size(); i++){
										eq("roleGroup.id", a[i])
								}
							}
						}
					}.collect { it.user }
					// groupList end

						// affiliateList start
						def b = Organization.createCriteria().list{
						if (affiliateList.size() != 0) {
							or{
								for (i=0; i<affiliateList.size(); i++){
										eq("name", affiliateList[i])
								}
							}
						}
					}.collect { it.id }

						// activityList start
						Boolean status = true;
						def e = User.createCriteria().list{
						if (activityList.size() != 0){
							or{
								for (i=0; i<activityList.size(); i++){
										if (activityList[i] == "Active"){
											status = true;
										} else{
											status = false;
										}
										eq("enabled", status)
								}
							}
						}
					}.collect { it.id }

					// get members by status
					def f = User.createCriteria().list{
						if (groupList.size() != 0){
							or{
								for (i=0; i<c.size(); i++){
										eq("username", c[i].username)
								}
							}
						}
						if (affiliateList.size() != 0){
							or{
								for (i=0; i<b.size(); i++){
										eq("affiliation.id", b[i])
								}
							}
						}
						if (activityList.size() != 0){
							or{
								for (i=0; i<e.size(); i++){
										eq("id", e[i])
								}
							}
						}
					}
						users = f
					}
				}
        else {
          query = User.where{}
					users = query.list(params)
        }

				[users: users,
         affiliations: affiliations,
         affiliationId: affiliationId,
         groupId: groupId,
         isEnabled: isEnabled]
	}

    def search(String username, String email) {
        def users = []
        if (username && username != "") {
            def user = User.findByUsername(username)
            if (user) {
                users.push(user)
            }
        } else if (email && email != "") {
            users = User.findAllByEmail(email)
        }
        render(view: "index", model: [users: users])
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
        def user = User.get(userId)
        if (!user) {
            render(view: "/404")
            return
        }
        user.properties = params
        def groups = params.list("groups")
        try {
            userService.updateByAdmin(user, groups)
            flash.message = "Success updating the user!"
        } catch(UserException e) {
            flash.message = e.message
        }
        redirect(action:"index")

    }

    def createUser(CreateUserCommand cmd) {
        def groupIds = []
        if (params.groupIds) {
            groupIds = params.list("groupIds")
        }

        if (cmd.hasErrors()) {
            render(view: "index", model: [users: [], cmd: cmd])
            return
        }
        try {
            def user = userService.create(cmd.email,
                            groupIds,
                            cmd.sendEmail
                            )
            request.message = "Success creating the user!"
            render(view: "index", model: [users: [user]])
        } catch (UserException e) {
            request.message = e.message
            render(view: "index", model: [users: []])
        }
    }
}

class CreateUserCommand implements grails.validation.Validateable {
    String email
    Boolean sendEmail

    static constraints = {
        email email: true, nullable: false, blank: false
    }
}
