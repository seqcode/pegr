<html>
<head>
  <title>My Projects</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <div id="addProject">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <button class="btn" data-toggle="collapse" data-target="#project-form"><span class="glyphicon glyphicon-plus"></span> Add Project</button>
        <div id="project-form" class="collapse">
            <g:hasErrors bean="${project}">
            <ul class="errors" role="alert">
                <g:eachError bean="${project}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form controller="project" action="save" method="post" role="form" >
                <g:render template="form"/>    
                <g:submitButton class="btn btn-primary" name="create" value="Create" />
            </g:form>
        </div>
    </div>

    <div id="allProjects">
        <ul class="list-group">
            <g:each var="userProject" in="${userProjects}">
            <li class="list-group-item">
                 <h4>
                     <g:link action="show" id="${userProject.project.id}">Project: ${userProject.project.name}</g:link> 
                     <span class="label label-default">${userProject.projectRole}</span>
                </h4>
                <p>Created: ${userProject.project.dateCreated}; Updated: ${userProject.project.lastUpdated}. </p>
            </li>
            </g:each>
        </ul>
        <g:paginate action="index" total="${projectCount}" max="25"/>
    </div>

     <script>
        $("#nav-projects").addClass("active");
     </script>
</body>
</html>


