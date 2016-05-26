<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container-fluid">
    <h3>Edit Sequencing Run</h3>
    <g:if test="${request.message}">
    <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors bean="${run}">
        <ul class="errors" role="alert">
            <g:eachError bean="${run}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form role="form" method="post" action="update" class="fields">
        <g:hiddenField name="runId" value="${run.id}"></g:hiddenField>
        <g:render template="statsForm"></g:render>
        <div class="row well text-center">
            <g:submitButton class="btn btn-primary" name="Save"/>
            <g:link action="show" id="${run.id}" class="btn btn-default">Cancel</g:link>
        </div>
    </g:form>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>