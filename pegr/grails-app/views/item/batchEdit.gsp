<html>
<head>
    <title>PEGR - Experiments</title> 
    <meta name="layout" content="main"/>
    <style>
        td {
            width: 10px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <h4>Samples</h4>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:form controller="item" action="batchSave">
            <g:hiddenField name="instanceId" value="${instanceId}"></g:hiddenField>
            <table class="table-bordered">
                <thead>
                    <tr>
                        <th>Item</th>
                        <th>Barcode</th>
                        <g:each in="${itemType.fieldList}" var="fieldName">
                            <th>${fieldName}</th>
                        </g:each>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${items}" var="item" status="n">
                    <tr>
                        <g:hiddenField name="items[${n}].id" value="${item.id}"></g:hiddenField>
                        <td>${item.name}</td>
                        <td>${item.barcode}</td>
                        <g:each in="${item?.type?.fieldList}" var="name">
                            <td><input name="items[${n}].${name}" value='<g:if test="${item.fieldMap}">${item?.fieldMap[name]}</g:if>'></td>
                        </g:each>
                    </tr>
                </g:each>
                </tbody>            
            </table>
            <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
        </g:form>        
    </div>
</body>
</html>