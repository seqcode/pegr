<html>
    <head>
        <title>PEGR Admin - User</title>
        <meta name="layout" content="admin"/>
    </head>
    <body>
        <div class="container-fluid">
            <h3>Edit User</h3>
            <g:form action="update" class="fields">
              <table>
                <tr>
                  <td>
                    <g:hiddenField name="userId" value="${user?.id}"></g:hiddenField>
                  </td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Username</label>
                  </td>
                  <td>
                    <g:textField name="username"  value="${user?.username}"/>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Full Name</label>
                  </td>
                  <td>
                    <g:textField name="fullName" value="${user?.fullName}"/>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Affiliation</label>
                  </td>
                  <td>
                    <g:select name="affiliation" from="${pegr.Organization.list()}" optionKey="id" value="${user?.affiliation}" noSelection="['':'--Choose--']" class="select2"></g:select>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Group</label>
                  </td>
                  <td>
                    <g:select name="groups" from="${pegr.RoleGroup.list()}" optionKey="id" value="${user?.authorities}" multiple="multiple"></g:select>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Status</label>
                  </td>
                  <td>
                    <select name="enabled" value="${user?.enabled}">
                        <option value="true">Active</option>
                        <option value="false">Inactive</option>
                    </select>
                  </td>
                </tr>
                <tr>
                  <td>
                    <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                    <g:link action="index" class="btn btn-default">Cancel</g:link>
                  </td>
                  <td>
                  </td>
                </tr>
              </table>
            </g:form>
        </div>
        <script>
            $(".select2").select2();
        </script>
    </body>
</html>
