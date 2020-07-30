// Place your Spring DSL code here
beans = {
  userDetailsService(grails.plugin.springsecurity.userdetails.GormUserDetailsService) {
    grailsApplication = ref('grailsApplication')
  }

  userDetailsByNameServiceWrapper(org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper) {
    userDetailsService = ref('userDetailsService')
  }

  preAuthenticatedAuthenticationProvider(org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider) {
    preAuthenticatedUserDetailsService = userDetailsByNameServiceWrapper
  }
    
  requestAttributeAuthenticationFilter(org.springframework.security.web.authentication.preauth.RequestAttributeAuthenticationFilter) {
     principalEnvironmentVariable = grailsApplication.config.getProperty('sso.principle')
     exceptionIfVariableMissing = false
     checkForPrincipalChanges = false
     authenticationManager = ref('authenticationManager')
   }

  requestHeaderAuthenticationFilter(org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter) {
    principalRequestHeader = grailsApplication.config.getProperty('sso.principle')
    exceptionIfHeaderMissing = false
    authenticationManager = ref('authenticationManager')
  }
}
