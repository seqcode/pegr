<html>
    <head>
        <meta name="layout" content="base"/>
    </head>
    <body>
        <div class="container">
            <g:if test="${request.message}">
                <div class="alert alert-danger">${request.message}</div>
            </g:if>
            <p>The password should have</p>
            <ol>
                <li>minimum eight characters</li>
                <li>at least one upper case English letter</li>
                <li>at least one lower case English letter</li>
                <li>at least one digit</li>
                <li>at least one special character, such as #?!@$%^&*-_</li>
            </ol>  
            <g:form controller="user" action="resetPassword" class="fields">
                <input type="hidden" name="token" value="${token}">
                <div>
                    <label>New Password</label>
                    <g:passwordField name="password"></g:passwordField>
                </div>
                <div>
                    <label>Re-enter Password</label>
                    <g:passwordField name="passwordRepeat"></g:passwordField>
                </div>
                <g:submitButton name="submit" value="Reset" class="btn btn-primary"></g:submitButton>
                <g:link controller="login" action="auth" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>        
    </body>
</html>