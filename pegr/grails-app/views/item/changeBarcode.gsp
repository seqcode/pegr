<html>
<head>
  <title>Cell Source</title> 
  <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
    </g:if>
    <h4>${item.name}</h4>
    <g:form class="fields" role="form" action="changeBarcode" method="post">
        <g:hiddenField name="id" value="${item.id}"></g:hiddenField>
        <div class="form-group">
            <label for="barcode">Barcode</label>
             <g:textField id="barcode" name="barcode" />
            <button type="button" onclick="getScan();"><span class="glyphicon glyphicon-qrcode"></span> Scan</button>
            <a href="#" onclick="refreshHash();"><span class="glyphicon glyphicon-refresh"></span></a>
        </div>
        <g:submitButton class="btn btn-primary" name="save" value="Save"/>
    </g:form>    
<script>
$("#nav-bench").addClass("active");
</script>
</body>
</html>