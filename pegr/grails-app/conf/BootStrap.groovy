import pegr.*

class BootStrap {
	def springSecurityService
	
		def init = { servletContext ->
			createAdminUserIfRequired()
		}
			
			
		private createAdminUserIfRequired() {
			println "Creating admin user"
			if (!User.findByUsername("labadmin")) {
				println "Fresh Database. Creating ADMIN user."
	
			def adminRole = new Role(authority: "ROLE_ADMIN").save(failOnError: true)
			def adminUser = new User(
						username: "labadmin",
						password: springSecurityService.encodePassword("admin"),
						enabled: true).save(failOnError: true)
				UserRole.create adminUser, adminRole
			}
			else {
				println "Existing admin user, skipping creation"
			}
		}
		
		def destroy = {
		}

}
