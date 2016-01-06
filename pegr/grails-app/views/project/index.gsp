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
    <g:paginate action="index" total="${projectCount}" max="25"/>

     <script>
        $("#nav-projects").addClass("active");
     </script>
</body>
</html>


