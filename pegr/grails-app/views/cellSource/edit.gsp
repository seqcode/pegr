<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
<div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${cellSource}" as="list"/>
        </div>
    </g:hasErrors>
    <h3>Edit Cell Source</h3>
    <g:form action="update" class="fields" role="form" method="post">
        <g:hiddenField name="cellSourceId" value="${cellSource?.cellSourceId}"></g:hiddenField>
        <g:hiddenField name="itemId" value="${cellSource?.itemId}"></g:hiddenField>
        <g:render template="form" model="['cellSource': cellSource]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" controller="cellSource" action="show" id="${cellSource?.cellSourceId}">Cancel</g:link>
    </g:form>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>