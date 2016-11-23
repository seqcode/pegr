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

  requestHeaderAuthenticationFilter(org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter) {
    principalRequestHeader = 'remote_user'
    exceptionIfHeaderMissing = false
    authenticationManager = ref('authenticationManager')
  }
}
