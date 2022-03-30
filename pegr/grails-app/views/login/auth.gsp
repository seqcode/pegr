<html>
<head>
    <title>Sign into Pegr</title>
    <meta name="layout" content="base"/>
</head>
<body>    
	<div class="container-fluid" id="login-form">
        <div class="text-center"><img src="/pegr/assets/PEGR_Logo.png" height="100%"/></div>
        <br>
        <g:if test="${flash.error}">
            <div class="alert alert-danger">${flash.error}</div>
        </g:if>
        <g:if test="${flash.message}">
            <div class="alert alert-info">${flash.message}</div>
        </g:if>
        <ul class="nav nav-tabs">
            <li class="nav-item active">
                <a class="nav-link" data-toggle="tab" href="#password-login">PEGR Login</a>
            </li>
        	<li class="nav-item">
                <a class="nav-link" href="${grailsApplication.config.getProperty('sso.url')}">Single Sign-On</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#register">Register</a>
            </li>
        </ul>
        <div class="tab-content" style="padding-top:10px">
            <div class="tab-pane active" id="password-login">
                <g:form uri="/login/authenticate" method="POST" role="form">
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
            <div class="tab-pane" id="register">
                <g:form uri="/user/selfRegister" method="POST" role="form">
                    <p>* Single Sign-On users only</p>
                    <div class="form-group">
                        <label for="fullName">Full Name </label> 
                        <g:textField class="form-control" name="fullName" value="${fullName}" placeholder="Enter Full Name"/>
                        <label for="username">Username </label> 
                        <g:textField class="form-control" name="username" value="${username}" placeholder="Enter Username"/>
                        <label for="email">Email </label> 
                        <g:textField class="form-control" name="email" value="${email}" placeholder="Enter Email"/>
                    </div>
                    <div class="form-group">
                        <g:submitButton name="signIn" value="Register" class="btn btn-primary"/>
                    </div>
                </g:form>
            </div>
        </div>
	</div>
</body>
