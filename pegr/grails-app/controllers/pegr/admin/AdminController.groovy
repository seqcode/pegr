package pegr.admin
import pegr.AdminCategory
import pegr.UtilityException
import pegr.UtilityService

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
    
    def mergeForm(String table) {
        def tables = ['user'] 
        [tables: tables, table: table]
    }
    
    def merge(String table, Long fromId, Long toId) {
        try {
            utilityService.mergeRowsInDb(table, fromId, toId)
            flash.message = "Success merging ${table} from ID-" + fromId + " to ID-" + toId + "!"
        } catch (UtilityException e) {
            flash.message = e.message
        }
        redirect(action: "mergeForm", params: [table: table])
    }
}

