<html>
<head>
    <title>PEGR - Sample</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div>
    <h4>Add Antibody</h4>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form class="fields" role="form" action="previewCellSource" >
        <g:hiddenField name="sampleId" value="${sampleId}"/>
        <div class=" ${hasErrors(bean: item, field: 'type', 'error')} ">
            <label>Type</label>
            <g:select id="type" name="typeId" from="${itemTypeOptions}" optionKey="id"></g:select>
        </div>      
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
        </div>
        <g:submitButton class="btn btn-primary" name="search" value="Search"/>
    </g:form>                    

    <script>
        $("#nav-metadata").addClass("active");
     </script>
</div>
</body>
</html>