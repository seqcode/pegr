// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    production {
        grails.logging.jul.usebridge = false
		
    }
}

grails.war.resources = { stagingDir ->
    delete { fileset dir: "${stagingDir}/files/" }
}

grails.gorm.default.constraints = {
    '*'(nullable: false)
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'pegr.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'pegr.UserRole'
grails.plugin.springsecurity.authority.className = 'pegr.Role'
grails.plugin.springsecurity.authority.groupAuthorityNameField = 'authorities'
grails.plugin.springsecurity.useRoleGroups = true
grails.plugin.springsecurity.logout.postOnly=false

grails.plugin.springsecurity.providerNames = ['daoAuthenticationProvider', 'preAuthenticatedAuthenticationProvider', 'anonymousAuthenticationProvider', 'rememberMeAuthenticationProvider']

grails.plugin.springsecurity.securityConfigType = grails.plugin.springsecurity.SecurityConfigType.InterceptUrlMap
//grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
    [pattern: '/assets/**',       access: ['permitAll']],
    [pattern: '/**/js/**',        access: ['permitAll']],
    [pattern: '/**/css/**',       access: ['permitAll']],
    [pattern: '/**/images/**',    access: ['permitAll']],
    [pattern: '/user/**',         access: ['permitAll']],
    [pattern: '/login',           access: ['permitAll']],
    [pattern: '/logout',          access: ['permitAll']],
    [pattern: '/login/**',        access: ['permitAll']],
    [pattern: '/logout/**',       access: ['permitAll']],
    [pattern: '/api/**',          access: ['permitAll']],
    [pattern: '/error',           access: ['permitAll']],
    [pattern: '/admin/**',        access: ['ROLE_ADMIN']],
    [pattern: '/*Admin/**',       access: ['ROLE_ADMIN']],
    [pattern: '/project/all/**',                access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/project/search/**',             access:  ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/project/create/**',             access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/protocol/importCSV/**',    access: ['ROLE_ADMIN']],
    [pattern: '/report/unknownIndex/**',        access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/report/analysisStatus/**',      access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/report/saveNotesAjax/**',       access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/report/runStatus/**',           access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/report/automatedReportList/**', access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/report/deleteAlignment/**',           access: ['ROLE_ADMIN']],
    [pattern: '/report/deleteAllAlignmentAjax/**',    access: ['ROLE_ADMIN']],
    [pattern: '/report/createReportForCohortAjax/**', access: ['ROLE_ADMIN']],
    [pattern: '/report/deleteReportForCohortAjax/**', access: ['ROLE_ADMIN']],
    [pattern: '/report/updateRunStatusAjax/**',       access: ['ROLE_ADMIN']],
    [pattern: '/report/manage/**',                    access: ['ROLE_ADMIN']],
    [pattern: '/report/saveDecisionTree/**',          access: ['ROLE_ADMIN']],
    [pattern: '/report/saveQcSettings/**',            access: ['ROLE_ADMIN']],
    [pattern: '/report/togglePreferredAlignment/**',  access: ['ROLE_ADMIN']],
    [pattern: '/report/updateReportStatusAjax/**',    access: ['ROLE_ADMIN']],
    [pattern: '/report/updateAnalysisCodeAjax/**',    access: ['ROLE_ADMIN']],
    [pattern: '/protocolInstanceBag/renderFile/**',   access: ['permitAll']],
    [pattern: '/protocolInstanceBag/reopenBag/**',    access: ['ROLE_ADMIN']],
    [pattern: '/protocolInstanceBag/deleteBag/**',    access: ['ROLE_ADMIN']],
    [pattern: '/protocolInstanceBag/**',    access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/sample/saveBarcode/**',     access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/sample/all/**',             access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/sample/search/**',          access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/sequenceRun/upload/**',     access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/updateQueue/**',access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/convertCsv/**', access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/continueCsv/**',access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/convertXlsx/**',access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/continueXlsx/**', access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/create/**',     access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/save/**',       access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/delete/**',     access: ['ROLE_ADMIN']],
    [pattern: '/sequenceRun/submitSequencingRequest/**',     access: ['ROLE_ADMIN']],
    [pattern: '/**',             access:  ['isAuthenticated()']] // everything else requires authenticated user
]

