package pegr.admin
import pegr.AdminCategory
import pegr.UserRoleGroup
import pegr.User
import pegr.UserService
import pegr.UserException

class UserAdminController {
	public static AdminCategory category = AdminCategory.OTHER

    def userService

	def index(Integer max, Long affiliationId, Long groupId, String isEnabled) {
				//Removed max parameter as to allow for all entries to populate datatable

        def affiliations = User.where{}.collect { it.affiliation }.unique()

        def users
        def totalCount

        // if (groupId) {
        //     def sort = params.sort ? ("user."+params.sort) : "user.fullName"
        //     users = UserRoleGroup.createCriteria().list(max: params.max, offset: params.offset, sort: sort, order: params.order) {
        //         eq("roleGroup.id", groupId)
        //     }.collect { it.user }
				//
        //     totalCount = UserRoleGroup.where { roleGroup.id == groupId }.count()
        // } else {
        //     def query
        //     if (affiliationId) {
        //         query = User.where { affiliation.id == affiliationId }
        //     } else if (isEnabled != null && isEnabled != "") {
        //         query = User.where { enabled == isEnabled }
        //     } else {
        //         query = User.where{}
        //     }
        //     users = query.list(params)
        //     totalCount = query.count()
        // }

				[users: users,
         totalCount: totalCount,
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

@grails.validation.Validateable
class CreateUserCommand {
    String email
    Boolean sendEmail

    static constraints = {
        email email: true, nullable: false, blank: false
    }
}
