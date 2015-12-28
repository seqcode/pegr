<html>
<head>
  <title>My Projects</title> 
  <meta name="layout" content="main"/>
</head>
<body>    
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <button class="btn" data-toggle="collapse" data-target="#project-form"><span class="glyphicon glyphicon-plus"></span> Add Project</button>
    <g:hasErrors bean="${project}">
    <ul class="errors" role="alert">
        <g:eachError bean="${project}" var="error">
        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
    </g:hasErrors>
    <form action="save" id="project-form" role="form" class="collapse">
        <g:render template="form"/>    
        <g:submitButton class="btn" name="save" value="Save" />
    </form>
    
    <ul class="list-group">
        <g:each var="userProject" in="${userProjects}">
        <li class="list-group-item">
             <h4>
                 <g:link action="show" id="${userProject.project_id}">Project: ${userProject.project.name}</g:link> 
                 <span class="label label-default">${userProject.projectRold}</span>
            </h4>
            <p>Created: ${userProject.project.dateCreated}; Updated: ${userProject.lastUpdated}. </p>
        </li>
        </g:each>
    </ul>
     <script>
        $("#nav-projects").addClass("active");
     </script>
</body>
</html>


