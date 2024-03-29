<html>
<head>
    <title>Workbench</title> 
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
<div>
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${item}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="update" class="fields" role="form" method="post">
        <g:hiddenField name="itemId" value="${item.id}"></g:hiddenField>
        <g:render template="/item/formWithBarcode" model="['item': item]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="show" id="${item.id}">Cancel</g:link>
    </g:form>
</div>
</body>
</html>