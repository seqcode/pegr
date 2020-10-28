// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format



grails.config.locations = [ "classpath:${appName}-config.properties",
                             "classpath:${appName}-config.groovy",
                             "file:/usr/local/${appName}/${appName}-config.properties",
                             "file:${userHome}/.grails/${appName}-config.properties",
                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}

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
		// axa677-20180306: If you are running pegr locally in development mode, you need the following line (ajax calls might work without it)
		// if you want to add this config in the external file "pegr-config.properties", go to .grails folder and edit that file if
		// it exists.
		// note: you should have only this line in either development or production (having this line in both might generate some conflict/errors)
		//grails.serverURL ="http://localhost:8080/${appName}"
    }
    production {
        grails.logging.jul.usebridge = false
		// axa677-20180306: This is important for the ajax calls to the controller actions with redirect to another action.
		// Without this line the ajax call will redirect over http by defualt and the call will be blocked because
		// grails security. To make the ajax call identified and coming from the app context we added this line.
		// This line will be commented if we already have it in the external config file "pegr-config.properties"
	    	// Important Note: if you add this line externally, make sure to remove the quotation marks.
		//grails.serverURL ="https://shaunline.vmhost.psu.edu/pegr"

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

grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
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
    [pattern: '/item/**',                   access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/antibody/**',               access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/cellSource/**',             access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/protocol/**',               access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
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
    [pattern: '/sequenceRun/**',            access: ['ROLE_MEMBER', 'ROLE_ADMIN']],
    [pattern: '/**',             access:  ['isAuthenticated()']] // everything else requires authenticated user
]

grails {
   mail {
     props = ["mail.smtp.auth":"true",
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]
   }
}
