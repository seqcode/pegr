<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="item"/>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">
    <h3>Edit Antibody</h3>
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${antibody}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="update" class="fields" role="form" method="post">
        <g:hiddenField name="antibodyId" value="${antibody.antibodyId}"></g:hiddenField>
        <g:render template="form" model="['object': antibody]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="show" id="${antibody.antibodyId}">Cancel</g:link>
    </g:form>
</div>
</body>
</html>