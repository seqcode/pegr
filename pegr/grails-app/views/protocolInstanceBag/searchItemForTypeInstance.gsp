<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div class="container-fluid">
    <g:link action="showInstance" id="${instanceId}"><span class="glyphicon glyphicon-menu-left"></span> Back</g:link>

    <h4>Add Item </h4>
    <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form class="fields" role="form" action="previewItemInInstance" >
        <g:hiddenField name="instanceId" value="${instanceId}"/>
        <div class="form-group">
            <label for="type">Type</label>
            <select id="type" name="typeId">
                <option value="${itemType.id}">${itemType.name}</option>
            <select>
        </div>    
        </div>        
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
        </div>
        <g:submitButton class="btn btn-primary" name="search" value="Search Or Create"/>
    </g:form>                    

    <script>
        $("#nav-bench").addClass("active");
     </script>
</div>
</body>
</html>