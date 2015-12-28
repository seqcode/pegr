<html>
<head>
    <title>Register</title>
    <meta name="layout" content="base" />
</head>
<body>
    <div class="container-fluid" id="register-form">
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${user}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="register" method="POST"  role="form">
        <div class="form-group ${hasErrors(bean: user, field: 'username', 'error')}">
            <label for="username">Username</label>
            <g:textField class="form-control" id="username" name="username" value="${user?.username}"></g:textField>
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'password', 'error')}">
            <label for="pwd">Password</label>
            <g:passwordField class="form-control" id="pwd" name="password"></g:passwordField>
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'passwordRepeat', 'error')} ">
            <label for="pwd2">Re-enter password</label>
            <g:passwordField class="form-control" id="pwd2" name="passwordRepeat"></g:passwordField>
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'fullname', 'error')}">
            <label for="fullname">Full name</label>
            <g:textField class="form-control" id="fullname" name="fullname" value="${user?.fullName}"></g:textField>
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'email', 'error')}">
            <label for="email">Email</label>
            <g:textField type="email" class="form-control" id="email" name="email" value="${user?.email}"></g:textField>
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'phone', 'error')}">
            <label for="phone">Phone</label>
            <g:textField type="tel" class="form-control" id="phone" name="phone"></g:textField>
        </div>
        <g:submitButton name="register" value="Register" class="btn btn-default"/>
    </g:form>
    </div>
</body>
</html>