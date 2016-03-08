package pegr

class SecurityFilters {

    def springSecurityService
    
    def filters = {
        
        // allow admin or anyone who has a role in this project to view 
        projectShow(controller:'project', action:'show') {
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
                
        AntibodyEdit(controller: 'antibody', action: 'edit|update|delete|editItem|updateItem|addBarcode') {
            before = {
                def antibodyId = params.long('antibodyId')
                def antibody = Antibody.get(antibodyId)
                def currUser = springSecurityService.currentUser
                if (currUser.authorities.any { it.authority == "ROLE_ADMIN" }
                   || antibody?.item?.user == currUser) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }

        CellSourceEdit(controller: 'cellSource', action: 'edit|update|addTreatment') {
            before = {
                def cellSourceId = params.long('cellSourceId')
                def cellSource = CellSource.get(cellSourceId)
                def currUser = springSecurityService.currentUser
                if (currUser.authorities.any { it.authority == "ROLE_ADMIN" }
                   || cellSource?.item?.user == currUser) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
        
        ItemEdit(controller: 'item', action: 'edit|update|upload|deleteImage|delete') {
            before = {
                def itemId = params.long('itemId')
                def item = Item.get(itemId)
                def currUser = springSecurityService.currentUser
                if (currUser.authorities.any { it.authority == "ROLE_ADMIN" }
                   || item?.user == currUser) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        }
    }
}
