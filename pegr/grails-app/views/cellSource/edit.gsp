<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div>
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${object}" as="list"/>
        </div>
    </g:hasErrors>
    <h3>Edit Cell Source</h3>
    <g:form action="update" class="fields" role="form" method="post">
        <g:hiddenField name="id" value="${object.id}"></g:hiddenField>
        <g:render template="form" model="['object': object]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" controller="item" action="show" id="${itemId}">Cancel</g:link>
    </g:form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>