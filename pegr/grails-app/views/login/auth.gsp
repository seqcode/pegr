<html>
<head>
    <title>Sign into Pegr</title>
    <meta name="layout" content="base"/>
</head>
<body>    
	<div class="container-fluid" id="login-form">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#">PEGR Login</a></li>
        	<li><a href="${grailsApplication.config.getProperty('sso.url')}">Single Sign-On</a></li>
        </ul>
        <g:form uri="/login/authenticate" method="POST" role="form">
            <g:if test="${flash.message}">
                <div class="alert alert-danger">${flash.message}</div>
            </g:if>
            <div class="form-group">
                <label for="username">Username </label> 
                <g:textField class="form-control" name="username" value="${username}" placeholder="Enter Username"/>
            </div>
            <div class="form-group">
                <label for="password">Password </label>
                <g:passwordField class="form-control" name="password" placeholder="Enter Password"/>
            </div>
            <div class="form-group">
                <label><g:checkBox name="remember-me" value="true"/> Remember me</label> | <g:link controller="user" action="forgetPassword">Forget Password</g:link>
            </div>
            <div class="form-group">
                <g:submitButton name="signIn" value="Sign in" class="btn btn-primary"/>
            </div>
        </g:form>
	</div>
</body>
