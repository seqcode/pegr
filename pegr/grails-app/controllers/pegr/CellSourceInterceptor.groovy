package pegr

class CellSourceInterceptor {

    def springSecurityService
    
    /**
     * This is executed before the following actionsin cell source controller. 
     * Only admin or the cell source user is authorized to make the change.
     */
    boolean before() { 
        if (actionName in ['edit',
                           'update',
                           'delete',
                           'editBarcode']){
            def cellSourceId = params.long('cellSourceId')
            def cellSource = CellSource.get(cellSourceId)
            def currUser = springSecurityService.currentUser
            if (currUser.isAdmin() || (cellSource?.item == null) || cellSource?.item?.user == currUser) {
                return true
            } else {
                render(view: '/login/denied')
                return false
            }
        } else {
            return true
        }
    }
    
    boolean after() { true }

    void afterView() {
        // no-op
    }
}
