package pegr.admin

class AdminController {

    def index() {    
		def controllers = [:]
	    grailsApplication.controllerClasses.each{ controller ->
	        def name = controller.name
	        if(name.endsWith('Admin') && name != 'Admin') {
	            controllers[controller.logicalPropertyName] = controller.naturalName.replace('Admin Controller', 'Manager')
            }
        }
        [controllers: controllers.sort()]
	}
}
