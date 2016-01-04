import pegr.*

class BootStrap {
	def springSecurityService
	
		def init = { servletContext ->
			createAdminUserIfRequired()
			
			createItemTypeIfRequired()
		}
			
		private createItemTypeIfRequired() {			
			if (!ItemType.findByName("Cell Source")) {
				println "Creating ItemType for Cell Source"
				def itemType = new ItemType(name: "Cell Source",
					objectType: "CellSource")
				itemType.save()
			}
			
			if (!ItemType.findByName("Antibody")) {
				println "Creating ItemType for Antibody"
				def itemType = new ItemType(name: "Antibody",
					objectType: "Antibody")
				itemType.save()
			}
			
			if (!ItemType.findByName("Sample")) {
				println "Creating ItemType for Sample"
				def itemType = new ItemType(name: "Sample",
					objectType: "Sample")
				itemType.save()
			}
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
