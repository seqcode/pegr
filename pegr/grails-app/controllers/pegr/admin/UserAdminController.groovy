package pegr.admin
import pegr.AdminCategory;
import pegr.User

class UserAdminController {
	public static AdminCategory category = AdminCategory.OTHER
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[users: User.list(params), count: User.count()]
	}
	
	def show() {
		
	}
	
	def search() {
		def userProps = User.metaClass.properties*.name
		def users = User.withCriteria {
			"${params.queryType}" {
				params.each { field, value ->
					if (userProps.grep(field) && value) {
						ilike(field, value)
					}
				}
			}
		}
		[users : users]
	}
	
}
