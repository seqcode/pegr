// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format



grails.config.locations = [ "classpath:${appName}-config.properties",
                             "classpath:${appName}-config.groovy",
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

grails.converters.encoding = "UTF-8"
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

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

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

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
		// axa677-20180306: added the following line
		// difference between file and rollingFile
		// file	FileAppender	Logs to a single file.
		// rollingFile	RollingFileAppender	Logs to rolling files, for example a new file each day.
		//file name:'pegrlog', file:'pegr.log', layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1} - %m%n'), maxFileSize:'5MB', maxBackupIndex:2, threshold: org.apache.log4j.Level.INFO

		// limit the size of file 'stacktrace.log'
		// axa677-20180306: On Shaunline, you should find this file in /usr/share/tomcat
		rollingFile name:'stacktrace', file:'stacktrace.log', layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1} - %m%n'), maxFileSize:'5MB', maxBackupIndex:2
    }

    error 	'org.codehaus.groovy.grails.web.servlet',        // controllers
           	'org.codehaus.groovy.grails.web.pages',          // GSP
           	'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           	'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           	'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           	'org.codehaus.groovy.grails.commons',            // core / classloading
           	'org.codehaus.groovy.grails.plugins',            // plugins
           	'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           	'org.springframework',
           	'org.hibernate'
	info   	'grails.app'

    root {
        warn 'stdout', 'stacktrace'
        additivity = true
    }
}

grails.war.resources = { stagingDir ->
    delete { fileset dir: "${stagingDir}/files/" }
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
    '/assets/**':       ['permitAll'],
    '/**/js/**':        ['permitAll'],
    '/**/css/**':       ['permitAll'],
    '/**/images/**':    ['permitAll'],
    '/user/**':         ['permitAll'],
    '/login/**':        ['permitAll'],
    '/logout/**':       ['permitAll'],
    '/api/**':          ['permitAll'],
    '/admin/**':        ['ROLE_ADMIN'],
    '/*Admin/**':       ['ROLE_ADMIN'],
    '/project/all/**':              ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/project/search/**':              ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/project/create/**':           ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/report/unknownIndex/**':      ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/report/analysisStatus/**':    ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/report/saveNotesAjax/**':     ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/report/runStatus/**':         ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/report/automatedReportList/**':['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/report/deleteAlignment/**':   ['ROLE_ADMIN'],
    '/report/deleteAllAlignmentAjax/**':   ['ROLE_ADMIN'],
    '/report/createReportForCohortAjax/**': ['ROLE_ADMIN'],
    '/report/deleteReportForCohortAjax/**': ['ROLE_ADMIN'],
    '/report/updateRunStatusAjax/**':           ['ROLE_ADMIN'],
    '/report/manage/**':            ['ROLE_ADMIN'],
    '/report/saveDecisionTree/**':          ['ROLE_ADMIN'],
    '/report/saveQcSettings/**':            ['ROLE_ADMIN'],
    '/report/togglePreferredAlignment/**':  ['ROLE_ADMIN'],
    '/report/updateReportStatusAjax/**':        ['ROLE_ADMIN'],
    '/report/updateAnalysisCodeAjax/**':    ['ROLE_ADMIN'],
    '/report/deletePurgedAlignments/**': ['ROLE_ADMIN'],
    '/protocolInstanceBag/renderFile/**':['permitAll'],
    '/protocolInstanceBag/reopenBag/**':['ROLE_ADMIN'],
    '/protocolInstanceBag/deleteBag/**':['ROLE_ADMIN'],
    '/protocolInstanceBag/**':  ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/item/**':                 ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/antibody/**':             ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/cellSource/**':             ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/protocol/**':             ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/sample/saveBarcode/**':   ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/sample/all/**':           ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/sample/search/**':        ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/sequenceRun/upload/**':     ['ROLE_ADMIN'],
    '/sequenceRun/updateQueue/**':     ['ROLE_ADMIN'],
    '/sequenceRun/convertCsv/**': ['ROLE_ADMIN'],
    '/sequenceRun/create/**':     ['ROLE_ADMIN'],
    '/sequenceRun/save/**':       ['ROLE_ADMIN'],
    '/sequenceRun/**':  ['ROLE_MEMBER', 'ROLE_ADMIN'],
    '/**':              ['isAuthenticated()'] // everything else requires authenticated user
]

grails {
   mail {
     props = ["mail.smtp.auth":"true",
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]
   }
}
