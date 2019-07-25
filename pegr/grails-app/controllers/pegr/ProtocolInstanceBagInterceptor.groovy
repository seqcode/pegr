package pegr

class ProtocolInstanceBagInterceptor {

    def springSecurityService
    
    boolean before() { 
        if (actionName in ['searchItemForBag',
                           'previewItemAndBag',
                           'addItemsToBag',
                           'addSubBagToBag',
                           'removeSampleFromBag']) {
            def bagId = params.long('bagId')
            def bag = ProtocolInstanceBag.get(bagId)
            def instances = ProtocolInstance.where { bag.id == bagId }.list(sort: "bagIdx", order: "asc")
            if ( instances.size() > 0 && instances[0].status == ProtocolStatus.INACTIVE) {
                return true
            } else {
                def message = "We have started processing the protocol instance bag and no changes are allowed. Please contact lab admin if you have further questions."
                render(view: '/login/denied', model: [message: message])
                return false
            }               
        } else if (actionName == 'update') {
            def bagId = params.long('bagId')
            def bag = ProtocolInstanceBag.get(bagId)
            if (bag.status != ProtocolStatus.COMPLETED) {
                return true
            } else {
                def message = "The protocol instance bag is completed and no changes are allowed. Please contact lab admin if you have further questions."
                render(view: '/login/denied', model: [message: message])
                return false
            }
        } else if (actionName in ['searchItemForInstance',
                                  'searchItemForTypeInstance',
                                  'previewItemInInstance',
                                  'addPoolToInstance',
                                  'addItemToInstance',
                                  'saveItemInInstance',
                                  'removeItemFromInstance',
                                  'addIndex',
                                  'searchAntibody',
                                  'previewAntibody',
                                  'addAntibodyToSample',
                                  'removeAntibody',
                                  'addChild',
                                  'removeChild',
                                  'completeInstance']) {
            def instanceId = params.long('instanceId') 
            def instance = ProtocolInstance.get(instanceId)
            if (instance?.user) {
                def currUser = springSecurityService.currentUser
                if (!currUser.isAdmin() && instance.user != currUser) {
                    render(view: '/login/denied')
                    return false
                }
            }                
            if (instance?.bag) {
                if (instance.bag.status == ProtocolStatus.COMPLETED) {
                    def message = "The protocol instance bag is completed and no changes are allowed. Please contact lab admin if you have further questions."
                    render(view: '/login/denied', model: [message: message])
                return false
                }
            } 
            return true
        } else {
            return true
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
