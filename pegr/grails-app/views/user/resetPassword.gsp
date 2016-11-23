<html>
    <head>
        <meta name="layout" content="base"/>
    </head>
    <body>
        <div class="container">
            <g:if test="${request.message}">
                <div class="alert alert-danger">${request.message}</div>
            </g:if>
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