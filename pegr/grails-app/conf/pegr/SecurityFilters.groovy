package pegr

class SecurityFilters {

    def springSecurityService
    
    def filters = {
        
        // allow admin or anyone who has role in this project to view 
        projectView(controller:'project', action:'show') {
            before = {
                def currUser = springSecurityService.currentUser
                if (currUser.id == 1) {
                    return true
                }
                def projectId = params.long('id')
                if (ProjectUser.where {project.id == projectId && user == currUser}.get(max: 1)) {
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
                def currUser = springSecurityService.currentUser
                if (currUser.id == 1) {
                    return true
                }
                def projectId = params.long('id')
                if (ProjectUser.where {project.id == projectId && user == currUser && projectRole == ProjectRole.OWNER}.get(max: 1)) {
                    return true                    
                } else {
                    flash.message = "Access denied!"
                    redirect(controller: "project", action: "index")
                    return false
                }
            }
        }

        AntibodyView(controller: 'antibody', action: 'show') {
            
        }
        
        AntibodyEdit(controller: 'antibody', action: 'edit|delete') {
            
        }
        
        CellSourceView(controller: 'cellSource', action: 'show') {
            
        }
        
        CellSourceEdit(controller: 'cellSource', action: 'edit|delete') {
            
        }
        
        ItemView(controller: 'item', action: 'show') {
            
        }
        
        ItemEdit(controller: 'item', action: 'edit|delete') {
            
        }
    }
}
