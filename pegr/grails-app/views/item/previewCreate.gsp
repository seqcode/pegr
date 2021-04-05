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
    <g:form action="preview" class="fields" role="form" method="post" useToken="true">
        <div class=" ${hasErrors(bean: item, field: 'type', 'error')} ">
            <label>Type</label>
            <g:select id="type" name="typeId" optionKey="id" from="${pegr.ItemType.list()}" noSelection="['null': '-- choose --']" style="width:250px"/>
        </div>
        <div class=" ${hasErrors(bean: item, field: 'barcode', 'error')} ">
            <label>Barcode</label>
            <g:field name="barcode" value="${barcode}" readonly="readonly" />
        </div>
        <g:submitButton class="btn btn-primary" name="create" value="Create"/>
        <g:link class="btn btn-default" action="list">Cancel</g:link>
    </g:form>
</div>
</body>
</html>