<html>
<head>
    <title>Project</title>
    <meta name="layout" content="main"/>
    <asset:javascript src="cookie.js"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="container-fluid">
        <br>
        <div>
            <h4>Merge Projects - Confirm Users</h4>
            <g:form controller="project" action="merge" class="fields">
                <div>
                    <label>Merge to:</label> ${projectName} (<g:if test="${mergeToProject}">existing</g:if><g:else>new</g:else> project) 
                    <g:hiddenField name="projectName" value="${projectName}"></g:hiddenField>
                </div>
                <div>
                    <label>Users in the merged project </label>
                    <p><i>You may change their project roles or remove them from the merged project by selecting "None".</i></p>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Affiliation</th> 
                                <th><a href="#" onclick="window.open('/pegr/help#project-role', 'Help: Project Role', 'width=600,height=400' )">Role <span class="glyphicon glyphicon-question-sign"></span></a></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each var="projectUser" in="${projectUsers}" status="n">
                            <tr>
                                <td>${projectUser.user.fullName} (${projectUser.user.username})</td>
                                <g:hiddenField name="userRoles[$n].userId" value="${projectUser.user.id}"></g:hiddenField>
                                <td>${projectUser.user.affiliation}</td>
                                <td><g:select name="userRoles[$n].role" from="${pegr.ProjectRole.values()}" keys="${pegr.ProjectRole.values()*.name()}" noSelection="['null': '-- None --']" value="${projectUser.projectRole}"/> </td>
                            </tr>
                            </g:each>
                            <tr></tr>
                        </tbody>
                    </table>
                </div>
                <g:submitButton class="btn btn-info" name="submit" value="Submit"></g:submitButton>
                <g:link action="cancelMerge" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        <br>
        <label>Merge from</label>
        <g:render template="overview" model="['projects':mergeFromProjects]"></g:render>
    </div>
    <script>
        $("#nav-projects").addClass("active");
    </script>
</body>
</html>
