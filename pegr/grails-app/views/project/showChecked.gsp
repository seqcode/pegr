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
            <h4>Merge Projects</h4>
            <g:form controller="project" action="confirmUsersInMergedProject" class="fields">
                <div>
                    <label>Merge to</label>
                    <input name="projectName" placeholder="Project Name">
                    <p><i>You may input the name of an existing project or a new project name. In the later case, a new project will be created.</i></p>
                </div>
                <g:submitButton class="btn btn-info" name="submit" value="Preview"></g:submitButton>
                <g:link action="cancelMerge" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        <br>
        <label>Merge from</label>
        <g:render template="overview" model="['projects':projects]"></g:render>
    </div>
    <script>
        $("#nav-projects").addClass("active");
    </script>
</body>
</html>
