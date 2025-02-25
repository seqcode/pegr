<html>
    <head>
        <title>PEGR Admin - User</title>
        <meta name="layout" content="admin"/>
    </head>
    <body>
        <div class="container-fluid">
            <g:if test="${request.message}">
                <div class="alert alert-danger">${request.message}</div>
            </g:if>
            <h3>Reset password for username ${user.username}</h3>
            <p>The password should have</p>
            <ol>
                <li>minimum eight characters</li>
                <li>at least one upper case English letter</li>
                <li>at least one lower case English letter</li>
                <li>at least one digit</li>
                <li>at least one special character, such as #?!@$%^&amp;*-_</li>
            </ol>  
            <g:form controller="userAdmin" action="saveUserPassword" class="fields" useToken="true">
                <input type="hidden" name="userId" value="${user.id}">
                <div>
                    <label>New Password</label>
                    <g:passwordField name="password"></g:passwordField>
                </div>
                <div>
                    <label>Re-enter Password</label>
                    <g:passwordField name="passwordRepeat"></g:passwordField>
                </div>
                <g:submitButton name="submit" value="Reset" class="btn btn-primary"></g:submitButton>
                <g:link controller="userAdmin" action="index" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>        
    </body>
</html>