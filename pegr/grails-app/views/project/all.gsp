<html>
<head>
  <title>My Projects</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <sec:ifAllGranted roles="ROLE_ADMIN">
    <div>
        <g:link action='index'  class="btn btn-info">My Projects</g:link>
        <g:link action='all'  class="btn btn-info active"></span>All Projects</g:link>
        <g:link action='create'  class="btn btn-info" ><span class="glyphicon glyphicon-plus"></span>Add Project</g:link>
    </sec:ifAllGranted>
    <ul class="list-group" id="allProjects">
        <g:render template="overview" collection="${projects}" var="project"/>
    </ul>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="project" action="all" max="15" total="${totalCount ?: 0}" />
    </div>
     <script>
        $("#nav-projects").addClass("active");
     </script>
</body>
</html>
