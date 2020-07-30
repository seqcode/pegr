package pegr

import pegr.*
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {
    def grailsApplication
    
	def springSecurityService
	
    def init = { servletContext ->
        createAdminUserIfRequired()

        createItemTypeIfRequired()
        
        createChoresIfRequired()
        
        if (grailsApplication.config.getProperty('sso.type') == "Attribute") {
            SpringSecurityUtils.clientRegisterFilter('requestAttributeAuthenticationFilter', SecurityFilterPosition.PRE_AUTH_FILTER) 
        } else if (grailsApplication.config.getProperty('sso.type') == "Header") {
            SpringSecurityUtils.clientRegisterFilter('requestHeaderAuthenticationFilter', SecurityFilterPosition.PRE_AUTH_FILTER) 
        }
    }

    private createItemTypeIfRequired() {			

        if (!ItemType.findByName("Antibody")) {
            println "Creating ItemType for Antibody"
            def itemTypeCategory = ItemTypeCategory.findByName("Antibody")
            if (!itemTypeCategory) {
                itemTypeCategory = new ItemTypeCategory(name: "Antibody", superCategory: ItemTypeSuperCategory.ANTIBODY)
                itemTypeCategory.save()
            }
            def itemType = new ItemType(name: "Antibody",
                category: itemTypeCategory)
            itemType.save()
        }

    }
    
    /**
     * Create rows in table "chores" to record 
     * (1) the sequence runs waiting in the queue to be analyzed and 
     * (2) the foler of the prior analyzed sequence run.
     */
    private createChoresIfRequired() {
        if (!Chores.findByName("RunsInQueue")) {
            println "Creating RunsInQueue"
            new Chores(name: "RunsInQueue").save(flush: true)                   
        }
        if (!Chores.findByName("PriorRunFolder")) {
            println "Creating PriorRunFoler"
            new Chores(name: "PriorRunFolder").save(flush: true)                   
        }
    }    

    private createAdminUserIfRequired() {
        println "Creating admin user"
        if (!User.findByUsername("labadmin")) {
            println "Fresh Database. Creating ADMIN user."

            def adminRole = new Role(authority: "ROLE_ADMIN").save(failOnError: true)
            def adminGroup = new RoleGroup(name: "Admin").save(failOnError: true)
            def adminGroupRole = new RoleGroupRole(role: adminRole, roleGroup: adminGroup).save(failOnError: true)
            def username = "labadmin"
            def origPassword = "labadmin"
            def adminUser = new User(
                    username: username,
                    password: springSecurityService.encodePassword(origPassword),
                    enabled: true).save(failOnError: true)
            UserRoleGroup.create adminUser, adminGroup
        }
        else {
            println "Existing admin user, skipping creation"
        }
    }

    def destroy = {
    }

}
