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
import pegr.PasswordRegistrationCommand

class UserAdminController {
	public static AdminCategory category = AdminCategory.OTHER

    def userService

	def index() {
        def query
        def users
        def affiliations = User.where{}.collect { it.affiliation }.unique()

        def groupList = []
        def affiliateList = []
        def activityList = []
        def c
    
        if (params.groupLoad != null) {
            if (params.groupLoad instanceof String) {
                groupList = [params.groupLoad]
            } else {
                groupList = params.groupLoad
            }            
        } 
        
        if (params.affiliateLoad != null) {
            if (params.affiliateLoad instanceof String) {
                affiliateList = [params.affiliateLoad]
            } else {
                affiliateList = params.affiliateLoad
            }            
        } 
        
        if (params.activityLoad != null) {
            if (params.activityLoad instanceof String) {
                activityList = [params.activityLoad]
            } else {
                activityList = params.activityLoad
            }
        }
            
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
            users = User.createCriteria().list{
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
        } else {
            query = User.where{}
            users = query.list(params)
        }
        
        [users: users,
         affiliations: affiliations,
         selectedGroupList: groupList,
         selectedAffiliateList: affiliateList,
         selectedActivityList: activityList]
	}

    def search(String username, String email) {
        def users = []
        if (username && username != "") {
            def user = User.findByUsername(username)
            if (user) {
                users << user
            }
        } else if (email && email != "") {
            users = User.findAllByEmail(email)
        }
        redirect(action: "index")
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
            redirect(action: "index")
            return
        }
        try {
            def user = userService.create(
                            cmd.email,
                            cmd.email,
                            groupIds,
                            cmd.sendEmail
                            )
            request.message = "Success creating the user!"
            redirect(action: "index")
        } catch (UserException e) {
            request.message = e.message
            redirect(action: "index")
        }
    }
    
    def mergeUsers(String fromUsernamesStr, String toUsername) {
        try {
            def fromUsernames = fromUsernamesStr.split(",").toList()
            for (i in 0..<fromUsernames.size()) {
                fromUsernames[i] = fromUsernames[i].trim()
            }
            userService.mergeUsers(fromUsernames, toUsername)
            flash.message = "Users have been successfully merged."
        } catch (UserException e) {
            flash.message = e.message            
        }
        redirect(action:"index")
    }
    
    /**
     * Export CSV 
     */
    def exportCsv() {
        final String filename = 'User.csv'
        def lines = User.findAll().collect { [
            it.id, 
            it.username?it.username:"", 
            it.fullName?'"' + it.fullName + '"':"", 
            it.email?it.email:"", 
            it.affiliation?it.affiliation:"", 
            it.address? '"' + it.address + '"':"",
            it.enabled?it.enabled:"",
            it.accountExpired?it.accountExpired:"",
            it.accountLocked?it.accountLocked:"",
            it.passwordExpired?it.passwordExpired:"",
            it.authorities?'"'+it.authorities+'"':"",
            it.roles?'"'+it.roles+'"':"",
            it.admin?it.admin:"",
            it.member?it.member:""
        ].join(',') } as List<String>;
        
        def outs = response.outputStream
        
        response.status = 200
        response.contentType = "text/csv;charset=UTF-8";
        response.setHeader "Content-disposition", "attachment; filename=${filename}"
        
        outs << "ID, Username, Full Name, Email, Affiliation, Address, enabled, Account Expired, Account Locked, Password Expired, Groups, Roles, Is Admin, Is Member\n"
        lines.each { String line ->
            outs << "${line}\n"
        }

        outs.flush()
        outs.close()

    }
    
    def updateUserPassword(Long userId) {
        def user = User.get(userId)
        render(view: "updateUserPassword", model: [user: user])
    }
    
    def saveUserPassword() {
        if(request.method=='POST') {
            withForm {
                def userId = params.userId as Long
                def user = User.get(userId)
                
                if (user == null) {
                    flash.message = "User not found!"
                    redirect(controller: "userAdmin", action: "index")
                }
                
                try {
                    def urc = new PasswordRegistrationCommand(password: params.password, passwordRepeat: params.passwordRepeat) 
                    userService.updatePassword(user, urc)
                    flash.message = "The password for ${user.username} has been changed."
                    redirect(controller: "userAdmin", action: "index")
                } catch (Exception e) {
                    request.message = e.message
                    render(view: "updateUserPassword", model: [user: user])
                }
            }
        } 
    }
}

class CreateUserCommand implements grails.validation.Validateable {
    String email
    Boolean sendEmail

    static constraints = {
        email email: true, nullable: false, blank: false
        sendEmail nullable: true, blank: true
    }
}
