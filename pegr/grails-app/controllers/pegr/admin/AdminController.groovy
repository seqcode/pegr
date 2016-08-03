package pegr.admin
import pegr.AdminCategory

class AdminController {
    def utilityService

    def index() {    
		
		def controllerGroups = [:]
		
		for (AdminCategory v : AdminCategory.values()) {
			controllerGroups[v] = [:]
		}
		
	    grailsApplication.controllerClasses.each{ controller ->
	        def name = controller.name
	        if(name.endsWith('Admin') && name != 'Admin') {
				def key = controller.getStaticPropertyValue('category', AdminCategory) ?: AdminCategory.OTHER
				controllerGroups[key][controller.logicalPropertyName] = controller.naturalName.replace('Admin Controller', '')
	        }
        }
        [controllerGroups: controllerGroups]
	}
    
    def merge(String table, Long fromId, Long toId) {
        utilityService.mergeRowsInDb(table, fromId, toId)
    }
}

