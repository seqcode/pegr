<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <h3>Sample #${sampleId}</h3>
    <g:form class="fields" role="form" controller="sample" action="saveBarcode" >
        <g:hiddenField name="sampleId" value="${sampleId}"></g:hiddenField>
        <div class="form-group">
             <label for="name">Name</label>
             <g:textField id="name" name="name" />
        </div>
        <div class="form-group">
            <label for="type">Type</label>
            <g:select id="type" name="type.id" optionKey="id" from="${types}" noSelection="['null': '-- choose --']" />
        </div>        
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span></button>
            <a href="#" onclick="refreshHash();"><span class="glyphicon glyphicon-refresh"></span></a>
        </div>
        <div class="form-group">
            <label for="location">Location</label>
             <g:textField id="location" name="location" />
        </div>
        <g:submitButton class="btn btn-primary btn-sm" name="save" value="Save"/>
        <g:link action="edit" params="[sampleId: sampleId]" class="btn btn-default">Cancel</g:link>
    </g:form> 
</body>
</html>
