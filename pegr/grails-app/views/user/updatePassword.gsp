<html>
<head>
    <title>Profile-password</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2>Profile</h2>
    <div class="profile">
        <h3>Change Password</h3>  
        <p>The password should have 5-20 characters.</p>
        <g:hasErrors bean="${user}">
            <div class="errors">
                <g:renderErrors bean="${user}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form action="updatePassword" method="POST" role="form" useToken="true">
            <div class="form-group ${hasErrors(bean: user, field: 'password', 'error')}">
                <label for="pwd">New password</label>
                <g:passwordField class="form-control"  id="pwd" name="password" ></g:passwordField>
            </div>
            <div class="form-group ${hasErrors(bean: user, field: 'passwordRepeat', 'error')} ">
                <label for="pwd2">Re-enter password</label>
                <g:passwordField class="form-control" id="pwd2" name="passwordRepeat"></g:passwordField>
            </div>
            <span>
                <g:submitButton name="update" value="Update" class="btn btn-primary"/>                   
                <g:link action='profile' class="btn btn-primary ">Cancel</g:link>
            </span>
        </g:form>
    </div>
</body>
</html>
