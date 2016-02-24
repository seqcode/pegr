import pegr.*

class BootStrap {
    def grailsApplication
    
	def springSecurityService
	
    def init = { servletContext ->
        createAdminUserIfRequired()

        createItemTypeIfRequired()
    }

    private createItemTypeIfRequired() {			

        if (!ItemType.findByName("Antibody")) {
            println "Creating ItemType for Antibody"
            def itemType = new ItemType(name: "Antibody",
                category: "ItemTypeCategory.ANTIBODY")
            itemType.save()
        }

        if (!SequencingPlatform.findByName("SOLiD")) {
            new SequencingPlatform(name: "SOLiD").save(flush: true)
        }

        if (!SequencingPlatform.findByName("Illumina GA")) {
            new SequencingPlatform(name: "Illumina GA").save(flush: true)
        }

        if (!SequencingPlatform.findByName("HiSeq 2000")) {
            new SequencingPlatform(name: "HiSeq 2000").save(flush: true)
        }

        if (!SequencingPlatform.findByName("NextSeq 500")) {
            new SequencingPlatform(name: "NextSeq 500").save(flush: true)
        }
    }

    private createAdminUserIfRequired() {
        println "Creating admin user"
        if (!User.findByUsername("labadmin")) {
            println "Fresh Database. Creating ADMIN user."

        def adminRole = new Role(authority: "ROLE_ADMIN").save(failOnError: true)
        def username = grailsApplication.config.defaultUser.username
        def origPassword = grailsApplication.config.defaultUser.password
        def adminUser = new User(
                    username: username,
                    password: springSecurityService.encodePassword(origPassword),
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
