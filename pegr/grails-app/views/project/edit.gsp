<html>
<head>
  <title>Project</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <h3>Edit Project</h3>
    <g:if test="${request.message}">
    <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors bean="${project}">
        <ul class="errors" role="alert">
            <g:eachError bean="${project}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action='edit' method="post" role="form" useToken="true">
        <g:render template='info' bean='${project}'/>
        <g:hiddenField name="projectId" value="${project.id}" />
        <g:submitButton name='save' value='Save' class="btn btn-primary"/>
        <g:link action='show' id='${project.id}' class="btn btn-default">Cancel</g:link>
    </g:form>
    <script>
        $("#nav-projects").addClass("active");
    </script>
</body>
</html>
