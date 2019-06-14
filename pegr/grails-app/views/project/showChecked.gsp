<html>
<head>
    <title>Project</title>
    <meta name="layout" content="main"/>
    <asset:javascript src="cookie.js"/>
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="container-fluid">
        <br>
        <div>
            <h4>Merge Projects</h4>
            <g:form controller="project" action="merge" class="fields">
                <div>
                    <label>Merge to</label>
                    <input name="projectName" placeholder="Project Name">
                </div>
                <g:submitButton class="btn btn-info" name="submit" value="Submit"></g:submitButton>
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
