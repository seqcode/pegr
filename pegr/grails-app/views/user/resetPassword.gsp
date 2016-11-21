<html>
    <head>
        <meta name="layout" content="base"/>
    </head>
    <body>
        <div class="container">
            <g:form controller="user" action="resetPassword">
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