<html>
<head>
  <title>My Projects</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div id="addProject">
        <g:link action='create'  class="btn btn-primary" ><span class="glyphicon glyphicon-plus"></span> Add Project</g:link>
    </div>
    <ul class="list-group" id="allProjects">
        <g:render template="overview" collection="${projects}" var="project"/>
    </ul>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="project" action="index" max="15" total="${totalCount ?: 0}" />
    </div>
     <script>
        $("#nav-projects").addClass("active");
     </script>
</body>
</html>


