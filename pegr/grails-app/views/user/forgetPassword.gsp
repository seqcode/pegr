<html>
    <head>
        <meta name="layout" content="base"/>
    </head>
    <body>
        <div class="container">
            <g:if test="${flash.message}">
                <div class="alert alert danger">${flash.message}</div>
            </g:if>
            <g:form controller="user" action="sendResetPasswordEmail">
                <p>Please enter your username and we will email you the link to reset your password. </p>
                <label>Email</label>
                <input name="email">
                <g:submitButton name="submit" value="Submit" class="btn btn-primary"></g:submitButton>
            </g:form>
        </div>
        
    </body>
</html>

