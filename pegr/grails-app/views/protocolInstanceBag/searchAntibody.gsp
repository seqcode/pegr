<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body  onhashchange="getHash()">
<div>
    <g:link action="showInstance" id="${instanceId}"><span class="glyphicon glyphicon-menu-left"></span> Back</g:link>

    <h4>Add Antibody</h4>
    <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form class="fields" role="form" action="previewAntibody" >
        <g:hiddenField name="instanceId" value="${instanceId}"/>
        <g:hiddenField name="itemId" value="${itemId}"/>
        <div class=" ${hasErrors(bean: item, field: 'type', 'error')} ">
            <label>Type</label>
            <select id="type" name="type.id">
                <option value="${antibodyTypeId}" selected>Antibody</option>
            </select>
        </div>      
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
            <a href="#" onclick="refreshHash();"><span class="glyphicon glyphicon-refresh"></span></a>
        </div>
        <g:submitButton class="btn btn-primary" name="search" value="Search"/>
    </g:form>                    

    <script>
        $("#nav-experiments").addClass("active");
     </script>
</div>
</body>
</html>