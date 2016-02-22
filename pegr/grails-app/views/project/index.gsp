<html>
<head>
  <title>My Projects</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <div id="addProject">
        <g:link action='create'  class="btn btn-primary" ><span class="glyphicon glyphicon-plus"></span> Add Project</g:link>
    </div>
    <ul class="list-group" id="allProjects">
        <g:render template="overview" collection="${userProjects}" var="userProject"/>
    </ul>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="project" action="index" total="${projectCount ?: 0}" />
    </div>
     <script>
        $("#nav-projects").addClass("active");
     </script>
</body>
</html>


