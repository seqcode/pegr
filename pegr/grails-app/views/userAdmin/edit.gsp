<html>
    <head>
        <title>PEGR Admin - User</title>
        <meta name="layout" content="admin"/>
    </head>
    <body>
        <div class="container-fluid">
            <h3>Edit User</h3>
            <p><label>Username</label> ${user?.username}</p>
            <p><label>Full name</label> ${user?.fullName} </p>
            <g:form action="update" class="fields">
                <g:hiddenField name="userId" value="${user?.id}"></g:hiddenField>
                <div>
                    <label>Affiliation</label>
                    <g:select name="affiliation" from="${pegr.Organization.list()}" optionKey="id" value="${user?.affiliation}" noSelection="['':'--Choose--']" class="select2"></g:select> 
                </div>
                <div>
                    <label>Authority</label>
                    <g:select name="roles" from="${pegr.Role.list()}" optionKey="id" value="${user?.authorities}" multiple="multiple"></g:select>
                </div>
                <div>
                    <label>Status</label>
                    <select name="enabled" value="${user?.enabled}">
                        <option value="true">Active</option>
                        <option value="false">Inactive</option>
                    </select>
                </div>
                <div>
                    <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                    <g:link action="index" class="btn btn-default">Cancel</g:link>
                </div>
            </g:form>
        </div>
        <script>
            $(".select2").select2();
        </script>
    </body>
</html>