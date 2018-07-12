<html>
<head>
  <title>My Projects</title>
  <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">
    <div>
        <g:link action='index'  class="btn btn-info active">My Projects</g:link>
        <g:link action='all'  class="btn btn-info" >All Projects</g:link>
        <g:link action='create'  class="btn btn-info" ><span class="glyphicon glyphicon-plus"></span>Add Project</g:link>
    </sec:ifAnyGranted>
    <div class="spacer"></div>
    <g:render template="overview" model="[projects:projects]" />
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="project" action="index" max="15" total="${totalCount ?: 0}" />
    </div>
     <script>
        $("#nav-projects").addClass("active");
     </script>
</body>
</html>
