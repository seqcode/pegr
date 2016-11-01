<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="item"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
<div class="container-fluid">
    <h3>Add Barcode to Cell Stock</h3>
    <g:if test="${request.message}">
        <div class="message" role="status">${request.message}</div>
    </g:if>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${item}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="editBarcode" class="fields" role="form" method="post" useToken="true">
        <g:hiddenField name="cellSourceId" value="${cellSourceId}"></g:hiddenField>
        <g:render template="/item/formWithBarcode" model="[item: item, itemTypeOptions: types]"></g:render>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
        <g:link class="btn btn-default" action="show" id="${cellSourceId}">Cancel</g:link>
    </g:form>
</div>
</body>
</html>