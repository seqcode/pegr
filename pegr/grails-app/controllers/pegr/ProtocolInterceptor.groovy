package pegr

class ProtocolInterceptor {

    def springSecurityService
    
    boolean before() { 
        if (actionName in ['edit',
                           'delete',
                           'upload',
                           'deleteFile']) {
            def user = springSecurityService.currentUser
            if (user.isAdmin()) {
                return true
            } else {
                def protocol = Protocol.get(params.long('id'))
                if (protocol.user == user) {
                    return true
                } else {
                    render(view: '/login/denied')
                    return false
                }
            }
        } 
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
