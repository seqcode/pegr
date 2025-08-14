<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>PEGR Admin - User</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h1><g:message code="default.button.delete.label" args="[entityName]" /></h1>
                <p>
                    Are you sure you want to delete the user <strong>${user.username}</strong>?
                </p>
                <g:form controller="userAdmin" action="deleteUser" method="POST">
                    <g:hiddenField name="userId" value="${user.id}" />
                    <g:submitButton name="delete" value="Delete" class="btn btn-danger" style="background-color: #d9534f"/>
                    <g:link controller="userAdmin" action="index" class="btn btn-default">Cancel</g:link>
                </g:form>
            </div>
        </div>
    </div>
</body>
</html>
