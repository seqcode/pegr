package pegr

class ItemInterceptor {

    def springSecurityService
    
    boolean before() { 
        if (actionName in ['edit',
                           'update',
                           'upload',
                           'deleteImage',
                           'delete',
                           'updateStatusAjax']) {
            def itemId = params.long('itemId')
            def item = Item.get(itemId)
            def currUser = springSecurityService.currentUser
            if (currUser.isAdmin() || item?.user == currUser) {
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