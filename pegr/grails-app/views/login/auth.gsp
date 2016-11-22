<html>
<head>
    <title>Sign into Pegr</title>
    <meta name="layout" content="base"/>
</head>
<body>    
	<div class="container-fluid" id="login-form">
	<g:form uri="/j_spring_security_check" method="POST" role="form">
        <g:if test="${flash.message}">
            <div class="alert alert-danger">${flash.message}</div>
        </g:if>
		<div class="form-group">
			<label for="j_username">Username </label> 
			<g:textField class="form-control" name="j_username" value="${username}" placeholder="Enter Username"/>
		</div>
		<div class="form-group">
			<label for="j_password">Password </label>
			<g:passwordField class="form-control" name="j_password" placeholder="Enter Password"/>
		</div>
		<div class="form-group">
			<label><g:checkBox name="_spring_security_remember_me" value="true"/> Remember me</label> | <g:link controller="user" action="forgetPassword">Forget Password</g:link>
		</div>
		<div class="form-group">
			<g:submitButton name="signIn" value="Sign in" class="btn btn-primary"/>
            <g:link controller="user" action="register" class="btn btn-default">Register</g:link>
		</div>
	</g:form>
	</div>
    <g:each in="${request.headerNames}">
        <p>${it} : ${request.getHeader(it)}</p>
    </g:each>
</body>
