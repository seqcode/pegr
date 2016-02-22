<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
<div class="container-fluid">
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${item}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="updateItem" class="fields" role="form" method="post" useToken="true">
        <g:hiddenField name="antibodyId" value="${antibodyId}"></g:hiddenField>
        <g:hiddenField name="itemId" value="${item.id}"></g:hiddenField>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="show" id="${antibodyId}">Cancel</g:link>
        <g:render template="/item/formWithBarcode" model="[item: item]"></g:render>
    </g:form>

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>