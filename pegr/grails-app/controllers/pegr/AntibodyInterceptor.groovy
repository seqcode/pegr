package pegr

class AntibodyInterceptor {

    def springSecurityService
    
    boolean before() { 
        if (actionName in ['edit',
                           'update',
                           'delete',
                           'editItem',
                           'updateItem',
                           'addBarcode']){
            def antibodyId = params.long('antibodyId')
            def antibody = Antibody.get(antibodyId)
            def currUser = springSecurityService.currentUser
            if (currUser.isAdmin() || (antibody?.item == null) || antibody?.item?.user == currUser) {
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
