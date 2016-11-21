<html>
<head>
    <title>Register</title>
    <meta name="layout" content="base" />
</head>
<body>
    <div class="container-fluid" id="register-form">
    <g:if test="${request.message}">
        <div class="errors">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${user}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="register" method="POST"  role="form">
        <div class="form-group ${hasErrors(bean: user, field: 'username', 'error')}">
            <label for="username">Username</label>
            <g:textField class="form-control" required="required" id="username" name="username" value="${user?.username}"></g:textField>
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'email', 'error')}">
            <label>Email</label>
            <input name="email" class="form-control" required="required" value="${user?.email}">
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'password', 'error')}">
            <label for="pwd">Password</label>
            <g:passwordField class="form-control" required="required" id="pwd" name="password" required="required"></g:passwordField>
        </div>
        <div class="form-group ${hasErrors(bean: user, field: 'passwordRepeat', 'error')} ">
            <label for="pwd2">Re-enter password</label>
            <g:passwordField class="form-control" required="required" id="pwd2" name="passwordRepeat"></g:passwordField>
        </div>
        <g:submitButton name="register" value="Register" class="btn btn-primary"/>
    </g:form>
    </div>
</body>
</html>