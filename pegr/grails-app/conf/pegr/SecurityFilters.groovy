package pegr

class SecurityFilters {

    def springSecurityService
    
    def filters = {
        projectView(controller:'project', action:'show') {
            before = {
                def currUser = springSecurityService.currentUser
                if (ProjectUser.where {project.id == id && user == currUser && projectRole == ProjectRole.OWNER}.get(max: 1)) {
                    return true                    
                } else {
                    flash.message = "Access denied!"
                    redirect(controller: "project", action: "index")
                    return false
                }
            }
        }
        
        projectEdit(controller:'project', action:'edit|addUserAjax|removeUserAjax') {
            before = {
                
            }
        }
    }
}
