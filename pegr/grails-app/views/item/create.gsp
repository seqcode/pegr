<html>
<head>
    <title>Workbench</title> 
</head>
<body>
<div class="container-fluid">
    <h4>Add Item </h4>
    <p>Item not found! You may save it as a new item.</p>
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${item}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" class="fields" role="form" method="post" useToken="true">
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="list" params="[typeId:item.type.id]">Cancel</g:link>
        <g:render template="form" model="['item':item]"/>
    </g:form>
</div>
</body>
</html>